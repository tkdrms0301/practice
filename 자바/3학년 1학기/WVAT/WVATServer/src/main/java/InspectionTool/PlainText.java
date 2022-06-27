package InspectionTool;

import lombok.SneakyThrows;
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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PlainText extends Vulnerability implements Runnable {
    boolean isPlantext;

    public PlainText() {
        super();
    }

    @Override
    public void check(String domain) {
        runModule(domain);
    }

    static boolean isFlag = true;
    static boolean isCapture = true;

    @Override
    public void run() {
        packetDump("WatWvt3421");
    }

    @SneakyThrows
    @Override
    public void runModule(String domain) {
        String id = "WatWvt3421", pw = "@@S23sd23";


        setSeleniumConfigSetting(); // 셀레니움 드라이버 설정 실행
        getDriver().get(domain);
        try {
            List<WebElement> element = getDriver().findElements(new By.ByTagName("a"));
            for (WebElement e : element) {
                if (e.getAttribute("href").equals(domain + "/#acField")) {
                    e.click();
                }
            }

            element = getDriver().findElements(new By.ByTagName("input"));
            for (WebElement iElement : element) {
                if (iElement.getAttribute("type").equals("text")) {
                    if (iElement.getAttribute("name").contains("user") || iElement.getAttribute("name").contains("id") || iElement.getAttribute("name").contains("uname")) {
                        iElement.sendKeys(id);
                        setInputValue(id);
                        for (WebElement jElemnt : element) {
                            if (jElemnt.getAttribute("type").equals("password")) {
                                jElemnt.sendKeys(pw);
                                jElemnt.sendKeys(Keys.ENTER);
                                setInputValue("ID : " + id + "       PW : " + pw);
                                setExists(true);
                                break;
                            }
                        }
                    }
                }
                if(isExists()) break;
            }
        } catch (Exception e) {
            System.out.println("PlainTextError");
            e.printStackTrace();
        } finally {
            isCapture =false;
            driver.close();
            driver.quit();
        }
    }


    public void packetDump(String input) {
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
            if(allDevs.get(i).getName().equals("\\Device\\NPF_{4E1042DD-1B87-4F99-8EDB-64ED2282BC05}")) //고정 디바이스
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
        Ethernet eth = new Ethernet();
        Http http = new Http();
        Ip4 ip = new Ip4();
        Tcp tcp = new Tcp();
        Payload payload = new Payload();
        PcapHeader header = new PcapHeader(JMemory.POINTER);
        JBuffer buf = new JBuffer(JMemory.POINTER);
        String result = " "; // 결과를 담을 변수

        int id = JRegistry.mapDLTToId(pcap.datalink());    // pcap의 datalink 유형을 jNetPcap의 프로토콜 ID에 맵핑

        isCapture = true;
        while (pcap.nextEx(header, buf) == Pcap.NEXT_EX_OK) // 헤더와 버퍼를 피어링
        {

            if(!isCapture) break;

            PcapPacket packet = new PcapPacket(header, buf);
            packet.getCaptureHeader();

            packet.scan(id); //새로운 패킷을 스캔하여 포함 된 헤더를 찾습니다

            if (packet.hasHeader(payload)) {
                isPlantext = payload.toHexdump().contains(input); //해당 inputValue가 존재한다면 true
                if (isPlantext) {
                    result = payload.toHexdump();  // hexdump를 저장
                    packet.hasHeader(http);
                    setWebMethod(http.fieldValue(Http.Request.RequestMethod));

                    pcap.close();
                    int idx1 = result.indexOf("W");
                    String res = result.substring((idx1 - 64), (idx1 + 85));
                    setResult(res);
                    setExists(true);
                    return;
                } else {
                    setResult(result);
                    setExists(false);
                }
            }
        }
        pcap.close();
    }
}