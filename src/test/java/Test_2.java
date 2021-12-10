import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Test_2 {
    // tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public static void main(String[] args) {
        int page = 1;	// 페이지 초기값
        try{
            while(true){
                // parsing할 url 지정(API 키 포함해서)
                String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?ServiceKey=DSIFuujRLymNXuOh6mIiMPZj987S4RiiOlsEVW19xiDT%2BhDxckUEmLiQ42%2BlOJuNJKvmTkfpW%2FuWJwqwfpEdtw%3D%3D&strSrch=130"+page;

                DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                Document doc = dBuilder.parse(url);

                // root tag
                doc.getDocumentElement().normalize();
                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                // 파싱할 tag
                NodeList nList = doc.getElementsByTagName("itemList");
                System.out.println("파싱할 리스트 수 : "+ nList.getLength());

                for(int temp = 0; temp < nList.getLength(); temp++){
                    Node nNode = nList.item(temp);
                    if(nNode.getNodeType() == Node.ELEMENT_NODE){

                        Element eElement = (Element) nNode;
                        System.out.println("######################");
                        //System.out.println(eElement.getTextContent());
                        System.out.println("노선ID  : " + getTagValue("busRouteId", eElement));
                        System.out.println("노선명  : " + getTagValue("busRouteNm", eElement));
                        System.out.println("노선유형 : " + getTagValue("routeType", eElement));
                        System.out.println("기점  : " + getTagValue("stStationNm", eElement));
                        System.out.println("종점  : " + getTagValue("edStationNm", eElement));
                        System.out.println("금일첫차시간  : " + getTagValue("firstBusTm", eElement));
                        System.out.println("금일막차시간  : " + getTagValue("lastBusTm", eElement));
                        System.out.println("운수사명  : " + getTagValue("corpNm", eElement));

                    }	// for end
                }	// if end

                page += 1;
                System.out.println("page number : "+page);
                if(page > nList.getLength()){
                    break;
                }
            }	// while end

        } catch (Exception e){
            e.printStackTrace();
        }	// try~catch end
    }	// main end


}
