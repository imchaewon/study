package com.example.java_._codingTest.opensearch;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Slf4j
@Configuration
public class OpenSearchConfig {
//	@Value("${opensearch.protocol}")
//	@Value("http")
	@Value("https")
	private String protocol;
//	@Value("${opensearch.host}")
//	@Value("10.10.10.237")
	@Value("34.22.77.58")
	private String host;
//	@Value("${opensearch.port}")
//	@Value("9200")
	@Value("5601")
	private Integer port;
	@Value("${opensearch.id}")
	private String id;
	@Value("${opensearch.pw}")
	private String pw;
//	@Value("${opensearch.useAuth}")
	@Value("false")
	private boolean useAuth;
//	@Value("${opensearch.connConfig.conTimeout}")
	@Value("300000")
	private Integer conTimeout;
//	@Value("${opensearch.connConfig.socketTimeout}")
	@Value("300000")
	private Integer socketTimeout;
//	@Value("${opensearch.connConfig.keepAlive}")
	@Value("300000")
	private Integer keepAlive;
//	@Value("${opensearch.connConfig.conReuse}")
	@Value("true")
	private boolean conReuse;

	@Bean(name = "osHighClient")
	public RestHighLevelClient createOpenSearchRestClient() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {

		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

		if (useAuth)
			credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(id, pw));

		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(
				null, new TrustManager[]{
						new X509TrustManager() {
							public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
							}

							public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
							}

							public X509Certificate[] getAcceptedIssuers() {
								return new X509Certificate[0];
							}
						}
				}
				, new java.security.SecureRandom()
		);

		TrustStrategy acceptingTrustStrategy = new TrustSelfSignedStrategy();
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

		RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, protocol))
				.setRequestConfigCallback(
						requestConfigBuilder -> requestConfigBuilder
								.setConnectTimeout(conTimeout)
								.setSocketTimeout(socketTimeout))
				.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
					@Override
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						return httpClientBuilder
								.setDefaultCredentialsProvider(credentialsProvider)
								.setConnectionReuseStrategy((response, context) -> conReuse)
								.setKeepAliveStrategy(((response, context) -> keepAlive))
								.setSSLContext(sslContext)  //ssl인증서 오류로 인해 추가
								.setSSLHostnameVerifier(new NoopHostnameVerifier());
					}
				});
		return new RestHighLevelClient(builder);
	}

	//////////////////////////////////////////////////////////////////////////////////////////
	// Initializing the client with SSL and TLS enabled using RestClient Transport
	// Opensearch java client 신규 API용.
	//////////////////////////////////////////////////////////////////////////////////////////
	@Bean(name = "osClient")
	public OpenSearchClient openSearchClientWithRestClient() {
		final HttpHost httpHost = new HttpHost(host, port, protocol);

		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		// 내 로컬에서는 os username, password 설정 없음
		if (useAuth) {
			credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(id, pw));
		}

		// SSL 인증서를 무시하는 SSLContext를 생성
		SSLContext sslContext = null;
		try {
			sslContext = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy()).build();
		} catch (Exception e) {
			// SSLContext 생성에 실패한 경우 예외 처리
			e.printStackTrace();
		}

		// SSLContext를 사용하여 SSL 인증서를 무시하도록 설정
		if (sslContext != null) {
			SSLContext finalSslContext = sslContext;
			final RestClient restClient = RestClient.builder(httpHost)
					.setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
							.setDefaultCredentialsProvider(credentialsProvider)
							.setSSLContext(finalSslContext)
							.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE))
					.build();

			final OpenSearchTransport transport = new RestClientTransport(restClient,
					new JacksonJsonpMapper());
			return new OpenSearchClient(transport);
		}

		return null; // SSLContext 생성에 실패한 경우 null 반환하거나 예외 처리를 진행할 수 있습니다.
	}
}