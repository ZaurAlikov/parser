import parser.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        UrlBuilder urlBuilder = new UrlBuilder();
        urlBuilder.init();

//        ESAutoParserImpl ESAutoParserImpl = new ESAutoParserImpl();
//        ESAutoParserImpl.processing(urlBuilder.geteSAutoUrlLists());

        EurodetalParserImpl evrodetalParser = new EurodetalParserImpl();
        evrodetalParser.processing(urlBuilder.getEvrodetalUrlLists());

//        ATuningParserImpl aTuningParser = new ATuningParserImpl();
//        aTuningParser.processing(urlBuilder.getaTuningUrlLists());
//
//        THStoreParserImpl thStoreParser = new THStoreParserImpl();
//        thStoreParser.processing(urlBuilder.gettHStoreUrlLists());
//
//        BagajnikParserImpl bagajnikParser = new BagajnikParserImpl();
//        bagajnikParser.processing(urlBuilder.getBagajnikUrlLists());
    }
}
