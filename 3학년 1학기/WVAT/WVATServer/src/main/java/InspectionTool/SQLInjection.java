package InspectionTool;

import Persistence.DAO.PayLoadDAO;
import Persistence.DTO.PayLoadDTO;
import Persistence.MybatisConnectionFactory;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.nio.JMemory;
import org.jnetpcap.packet.JRegistry;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SQLInjection extends Vulnerability implements Runnable {
    public SQLInjection() {
        super();
    }

    static int stateCode = 0;
    static boolean isFlag = true;
    static boolean isCapture = true;

    @Override
    public void check(String domain) {
        runModule(domain);
    }

    @Override
    public void run() {
        packetDump();
    }

    @Override
    public void runModule(String domain) {
        PayLoadDAO payLoadDAO = new PayLoadDAO(MybatisConnectionFactory.getSqlSessionFactory());
        List<PayLoadDTO> payLoadDTO = payLoadDAO.randomAndOrderPayLoadSelectPrint("sqlInjection");
        String res = " ";
        boolean isSqlInjection = false;
        setSeleniumConfigSetting();

        getDriver().get(domain);

        try {
            List<WebElement> elements = getDriver().findElements(new By.ByTagName("a"));
            for(WebElement e : elements){
                if(e.getAttribute("href").equals(domain+"/#acField") || e.getAttribute("href").equals(domain+"/#myModal")){
                    e.click();
                }
            }

            elements = getDriver().findElements(By.tagName("input"));

            for (int i = 0; i < elements.size(); i++) {
                elements = getDriver().findElements(By.tagName("input"));
                if (elements.get(i).getAttribute("type").equals("text")) {
                    if (elements.get(i).getAttribute("name").contains("user") || elements.get(i).getAttribute("name").contains("id") || elements.get(i).getAttribute("name").contains("uname")) {
                        for (int j = 0; j < payLoadDTO.size(); j++) {
                            elements = getDriver().findElements(By.tagName("input"));
                            elements.get(i).sendKeys(payLoadDTO.get(j).getPayload());

                            for(int k =0; k < elements.size(); k++){
                                if(elements.get(k).getAttribute("type").equals("password")){
                                    elements.get(k).sendKeys("@@S23sd23");
                                    elements.get(k).sendKeys(Keys.ENTER);
                                    break;
                                }
                            }

                            System.out.println(stateCode);
                            if (stateCode == 302) {
                                stateCode = 0;
                                getDriver().navigate().refresh();

                                elements = getDriver().findElements(new By.ByTagName("a"));
                                for(WebElement e : elements){
                                    if(e.getAttribute("href").equals(domain+"/#acField") || e.getAttribute("href").equals(domain+"/#myModal")){
                                        e.click();
                                    }
                                }
                                continue;
                            }else if(!(ExpectedConditions.alertIsPresent().apply(driver)==null)){
                                driver.switchTo().alert().accept();
                                break;
                            }else{
                                res = payLoadDTO.get(j).getPayload();
                                elements = getDriver().findElements(By.tagName("input"));
                                payLoadDAO.updatePayLoadCount(payLoadDTO.get(j).getPayload());
                                isSqlInjection = true;
                                break;
                            }
                        }
                    }
                }
                if (isSqlInjection) break;
            }

            if (isSqlInjection) {
                setInputValue(res);
                setResult("200 OK");
                setExists(true);
                setStateCode("200");
            } else {
                setInputValue("Blind SQL Injection Syntax");
                setResult(" ");
                setExists(false);
                isCapture = false;
            }
        } catch (Exception e) {
            System.out.println("SQL Exception");
            e.printStackTrace();
        } finally {
            isCapture = false;
            getDriver().close();
            getDriver().quit();
        }
    }

    public void packetDump() {
        ArrayList<PcapIf> allDevs = new ArrayList<PcapIf>();
        // 디바이스를 담을 변수를 ArrayList로 생성
        StringBuilder errbuf = new StringBuilder();
        // 에러처리

        int r = Pcap.findAllDevs(allDevs, errbuf);
        // 접근가능한 디바이스를 첫 번째인자에 담는다, 두 번째인자는 에러처리

        if (r == Pcap.NOT_OK || allDevs.isEmpty()) {
            System.out.println("네트워크 장치 찾기 실패." + errbuf.toString());
            return;
        }    // 예외처리

        System.out.println("< 탐색된 네트워크 Device >");

        int myDevice = 0;
        for (int i = 0; i < allDevs.size(); i++) {    // 탐색한 장비를 출력
            String description = (allDevs.get(i).getDescription() != null) ? allDevs.get(i).getDescription() : "장비에 대한 설명이 없습니다.";
            System.out.printf("[%d번]: %s [%s]\n", i, allDevs.get(i).getName(), description);
            if (allDevs.get(i).getName().equals("\\Device\\NPF_{4E1042DD-1B87-4F99-8EDB-64ED2282BC05}")) //고정 디바이스
                myDevice = i; //내 디바이스 선택
        }

        PcapIf device = allDevs.get(myDevice);
        System.out.printf("선택된 장치: %s\n", (device.getDescription() != null) ?
                device.getDescription() : device.getName());

        int snaplen = 64 * 1024; //65536Byte만큼 패킷을 캡쳐
        int flags = Pcap.MODE_NON_PROMISCUOUS; // 무차별모드
        int timeout = 10 * 500; // timeout 5second
        Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

        if (pcap == null) {
            System.out.println("Network Device Access Failed. Error: " + errbuf.toString());
            return;
        }

        // 여기서 부터 계층별 객체 생성
        Http http = new Http();
        PcapHeader header = new PcapHeader(JMemory.POINTER);
        JBuffer buf = new JBuffer(JMemory.POINTER);

        int id = JRegistry.mapDLTToId(pcap.datalink());    // pcap의 datalink 유형을 jNetPcap의 프로토콜 ID에 맵핑
        isFlag = false;
        isCapture = true;

        while (pcap.nextEx(header, buf) == Pcap.NEXT_EX_OK) // 헤더와 버퍼를 피어링
        {
            if(!isCapture) break;

            PcapPacket packet = new PcapPacket(header, buf);
            packet.getCaptureHeader();

            packet.scan(id); //새로운 패킷을 스캔하여 포함 된 헤더를 찾습니다

            if (packet.hasHeader(http)) {
                String code = " ";
                code = http.fieldValue(Http.Response.ResponseCode);
                if (code != null && code.equals("302")) {
                    stateCode = 302;
                }else if(code != null && code.equals("200") || code == null){
                    continue;
                }else{
                    break;
                }
            }
        }
        pcap.close();
    }
}