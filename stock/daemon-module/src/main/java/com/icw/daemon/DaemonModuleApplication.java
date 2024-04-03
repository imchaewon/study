package com.icw.daemon;

import com.icw.daemon.service.DomesticService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
@RequiredArgsConstructor
public class DaemonModuleApplication {
	private final DomesticService domesticService;

	public static void main(String[] args) {
		SpringApplication.run(DaemonModuleApplication.class, args);
	}

	@PostConstruct
	void m1() {
		domesticService.collect(new String[]{"005930","000660","373220","207940","005380","005935","000270","068270","005490","051910","035420","028260","006400","105560","035720","003670","055550","012330","086790","066570","032830","138040","003550","000810","323410","034730","450080","015760","096770","011200","018260","033780","259960","017670","316140","024110","030200","009150","047050","034020","329180","010130","022100","402340","352820","003490","009540","010950","042700","090430","012450","005830","042660","161390","086280","326030","377300","010140","005387","011170","009830","005070","267250","251270","001570","066970","006800","454910","051900","000100","047810","302440","361610","004020","241560","028050","180640","036570","011070","097950","078930","032640","011780","307950","128940","034220","029780","021240","267260","071050","271560","005940","005385","000720","016360","035250","003410","018880","004990","000120","011790","039490","064350","001450","006260","008930","272210","088350","001040","079550","383220","052690","138930","010620","036460","112610","002790","081660","175330","051915","008770","028670","002380","007070","023530","282330","012750","004370","000880","000990","111770","139480","030000","010060","010120","014680","073240","007660","009420","004170","026960","002710","006040","047040","000240","017800","011210","020150","007310","051600","204320","042670","001740","005850","139130","298040","137310","003620","000150","012510","375500","298050","000080","006110","185750","248070","018670","336260","003230","069620","006280","003090","003690","298020","108320","457190","006360","004800","145720","192820","005420","069960","004000","001440","294870","285130","009970","280360","005300","009240","353200","004490","120110","008730","161890","085620","267270","000210","001120","103140","000400","023590","395400","192080","014820","003240","089860","181710","006650","039130","100090","003530","000670","036560","001800","089590","009900","271940","001430","082640","002350","214320","010780","300720","007340","003030","195870","093370","105630","336370","456040","003850","006120","365550","069260","003540","348950","192650","001060","114090","002840","016380","066575","330590","005250","001530","009450","000815","005440","025540","281820","002240","003280","033240","032350","178920","001680","344820","079160","000640","005880","268280","019170","007700","272450","030190","064960","229640","001720","161000","460860","049770","031430","000070","000370","030610","090460","403550","091810","210980","192400","082740","170900","200880","286940","381970","293940","383800","00680K","003470","017960","001470","005180","057050","003570","005610","103590","145990","950210","014830","020000","034310","012630","007810","034120","241590","284740","058650","001725","097520","001390","900140","249420","377740","003000","018250","010690","001940","183190","017940","011930","357120","029530","003920","123890","002030","004690","003300","090435","009290","002310","432320","005810","033270","093050","017810","453340","448730","126560","084690","006405","004430","071320","306200","000430","004700","003545","027410","006220","000540","025860","001200","005389","108670","003520","002320","016800","000490","001820","007690","093230","451800","060980","001340","001780","000680","298690","011810","005690","005090","051905","00088K","036530","003220","003960","012030","084680","005010","00104K","092220","084010","004380","001880","003120","008490","001510","058430","072710","029460","088260","001630","001790","013890","000480","002960","075580","017390","037560","005950","001500","006390","097230","071970","126720","104700"});
	}
}
