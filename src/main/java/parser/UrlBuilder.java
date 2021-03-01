package parser;

import model.UrlList;

import java.util.ArrayList;
import java.util.List;

public class UrlBuilder {

    private final List<UrlList> eSAutoUrlLists = new ArrayList<>();
    private final List<UrlList> evrodetalUrlLists = new ArrayList<>();
    private final List<UrlList> aTuningUrlLists = new ArrayList<>();
    private final List<UrlList> tHStoreUrlLists = new ArrayList<>();
    private final List<UrlList> bagajnikUrlLists = new ArrayList<>();

    public void init() {
        eSAutoInit();
        evrodetalInit();
        aTuningInit();
        tHStoreInit();
        bagajnikInit();
    }

    private void aTuningInit() {
        UrlList carBoxesUrlList = new UrlList("Автомобильные боксы", true);
        List<String> carBoxes = new ArrayList<>();
        carBoxes.add("https://a-tuning.ru/catalog/avtomobilnye_boksy/?count=100");
        carBoxesUrlList.getUrlList().addAll(carBoxes);
        aTuningUrlLists.add(carBoxesUrlList);

        UrlList bikeRackUrlList = new UrlList("Велокрепления", true);
        List<String> bikeRack = new ArrayList<>();
        bikeRack.add("https://a-tuning.ru/catalog/krepleniya_dlya_velosipedov/?count=100");
        bikeRackUrlList.getUrlList().addAll(bikeRack);
        aTuningUrlLists.add(bikeRackUrlList);

        UrlList skyFastenersUrlList = new UrlList("Крепления для лыж и сноубордов", true);
        List<String> skyFasteners = new ArrayList<>();
        skyFasteners.add("https://a-tuning.ru/catalog/krepleniya_dlya_lyzh_snoubordov/?count=100");
        skyFastenersUrlList.getUrlList().addAll(skyFasteners);
        aTuningUrlLists.add(skyFastenersUrlList);

        UrlList cargoBasketsUrlList = new UrlList("Грузовые корзины", true);
        List<String> cargoBaskets = new ArrayList<>();
        cargoBaskets.add("https://a-tuning.ru/catalog/gruzovye_korziny/?count=100");
        cargoBasketsUrlList.getUrlList().addAll(cargoBaskets);
        aTuningUrlLists.add(cargoBasketsUrlList);

        UrlList waterSportsUrlList = new UrlList("Крепления для водного спортивного снаряжения", true);
        List<String> waterSports = new ArrayList<>();
        waterSports.add("https://a-tuning.ru/catalog/krepleniya_dlya_lodok_kayakov/?count=100");
        waterSportsUrlList.getUrlList().addAll(waterSports);
        aTuningUrlLists.add(waterSportsUrlList);

        UrlList accessoriesUrlList = new UrlList("Аксессуары", true);
        List<String> accessories = new ArrayList<>();
        accessories.add("https://a-tuning.ru/catalog/aksessuary_dlya_bagazhnikov/?count=100");
        accessories.add("https://a-tuning.ru/catalog/aksessuary_dlya_boksov_1/?count=100");
        accessories.add("https://a-tuning.ru/catalog/organayzery_i_sumki/?count=100");
        accessoriesUrlList.getUrlList().addAll(accessories);
        aTuningUrlLists.add(accessoriesUrlList);
    }

    private void evrodetalInit() {
        UrlList carBoxesUrlList = new UrlList("Автомобильные боксы", true);
        List<String> carBoxes = new ArrayList<>();
        carBoxes.add("https://bagazhniki.su/catalog/avtomobilnye-boksy");
        carBoxes.add("https://bagazhniki.su/catalog/avtomobilnye-boksy?st=18");
        carBoxesUrlList.getUrlList().addAll(carBoxes);
        evrodetalUrlLists.add(carBoxesUrlList);

//        UrlList accessoriesUrlList = new UrlList("Аксессуары", false);
//        List<String> accessories = new ArrayList<>();
//        accessories.add("https://bagazhniki.su/catalog/aksessuary/chekhol-dlya-boksa-l");
//        accessories.add("https://bagazhniki.su/catalog/aksessuary/chekhol-dlya-boksa-xl");
//        accessories.add("https://bagazhniki.su/catalog/camping-sumki/magnum-bag");
//        accessories.add("https://bagazhniki.su/catalog/camping-sumki/magnum-bag-nose");
//        accessoriesUrlList.getUrlList().addAll(accessories);
//        evrodetalUrlLists.add(accessoriesUrlList);

//        urlsList.add("https://bagazhniki.su/catalog/camping-sumki");
    }

    private void eSAutoInit() {
        UrlList carBoxesUrlList = new UrlList("Автомобильные боксы", true);
        List<String> carBoxes = new ArrayList<>();
        carBoxes.add("https://es-auto.ru/car-boxes/thule/");
//        carBoxes.add("https://es-auto.ru/car-boxes/atlant-rossiya/");
//        carBoxes.add("https://es-auto.ru/car-boxes/menabo-italiya/");
        carBoxesUrlList.getUrlList().addAll(carBoxes);
        eSAutoUrlLists.add(carBoxesUrlList);
//
//        UrlList bikeRackUrlList = new UrlList("Велокрепления", true);
//        List<String> bikeRack = new ArrayList<>();
//        bikeRack.add("https://es-auto.ru/bike-rack/atlant/");
//        bikeRack.add("https://es-auto.ru/bike-rack/buzz-rack/");
//        bikeRack.add("https://es-auto.ru/bike-rack/menabo/");
//        bikeRack.add("https://es-auto.ru/accessories/");
//        bikeRack.add("https://es-auto.ru/bike-rack/yakima/");
//        bikeRackUrlList.getUrlList().addAll(bikeRack);
//        eSAutoUrlLists.add(bikeRackUrlList);
//
//        UrlList skyFastenersUrlList = new UrlList("Крепления для лыж и сноубордов", true);
//        List<String> skyFasteners = new ArrayList<>();
//        skyFasteners.add("https://es-auto.ru/ski-fasteners/atlant-rossiya/");
//        skyFasteners.add("https://es-auto.ru/ski-fasteners/menabo-italiya/");
//        skyFasteners.add("https://es-auto.ru/ski-fasteners/yakima-amerika/");
//        skyFasteners.add("https://es-auto.ru/ski-fasteners/buzz-rack/");
//        skyFastenersUrlList.getUrlList().addAll(skyFasteners);
//        eSAutoUrlLists.add(skyFastenersUrlList);
//
//        UrlList cargoBasketsUrlList = new UrlList("Грузовые корзины", true);
//        List<String> cargoBaskets = new ArrayList<>();
//        cargoBaskets.add("https://es-auto.ru/cargo-baskets/atlant/");
//        cargoBaskets.add("https://es-auto.ru/cargo-baskets/buzz-rack/");
//        cargoBaskets.add("https://es-auto.ru/cargo-baskets/menabo/");
//        cargoBasketsUrlList.getUrlList().addAll(cargoBaskets);
//        eSAutoUrlLists.add(cargoBasketsUrlList);
//
//        UrlList waterSportsUrlList = new UrlList("Крепления для водного спортивного снаряжения", true);
//        List<String> waterSports = new ArrayList<>();
//        waterSports.add("https://es-auto.ru/water-sports-eq/menabo-italiya/");
//        waterSports.add("https://es-auto.ru/water-sports-eq/yakima-ssha/");
//        waterSportsUrlList.getUrlList().addAll(waterSports);
//        eSAutoUrlLists.add(waterSportsUrlList);
//
//        UrlList accessoriesUrlList = new UrlList("Аксессуары", true);
//        List<String> accessories = new ArrayList<>();
//        accessories.add("https://es-auto.ru/accessories/atlant-rossiya/");
//        accessories.add("https://es-auto.ru/accessories/buzz-rack/");
//        accessories.add("https://es-auto.ru/accessories/menabo/");
//        accessories.add("https://es-auto.ru/accessories/yakima-ssha/");
//        accessories.add("https://es-auto.ru/accessories/vezdekhod-rossiya/");
//        accessoriesUrlList.getUrlList().addAll(accessories);
//        eSAutoUrlLists.add(accessoriesUrlList);

//        categories.put("Автомобильные боксы на фаркоп", new CarBoxesOnFarcop());
//        categories.put("Автобагажники", new CarTrunks());
    }

    private void tHStoreInit() {
        UrlList urbanBackpacksUrlList = new UrlList("Городские рюкзаки для ноутбуков", true);
        List<String> urbanBackpacks = new ArrayList<>();
        urbanBackpacks.add("https://thstore.ru/catalog/bags-and-suitcases/urban-backpacks/");
        urbanBackpacksUrlList.getUrlList().addAll(urbanBackpacks);
        tHStoreUrlLists.add(urbanBackpacksUrlList);

        UrlList sportBagsUrlList = new UrlList("Спортивные сумки", true);
        List<String> sportBags = new ArrayList<>();
        sportBags.add("https://thstore.ru/catalog/bags-and-suitcases/suitcases/sportivnye-sumki/");
        sportBagsUrlList.getUrlList().addAll(sportBags);
        tHStoreUrlLists.add(sportBagsUrlList);

        UrlList suitcasesUrlList = new UrlList("Чемоданы", true);
        List<String> suitcases = new ArrayList<>();
        suitcases.add("https://thstore.ru/catalog/bags-and-suitcases/suitcases/chemodany-na-kolesakh/");
        suitcases.add("https://thstore.ru/catalog/bags-and-suitcases/suitcases/bags-thule-revolve/");
        suitcases.add("https://thstore.ru/catalog/bags-and-suitcases/suitcases/bags-thule-subterra/");
        suitcasesUrlList.getUrlList().addAll(suitcases);
        tHStoreUrlLists.add(suitcasesUrlList);

        UrlList cyclingBackpacksUrlList = new UrlList("Велорюкзаки", true);
        List<String> cyclingBackpacks = new ArrayList<>();
        cyclingBackpacks.add("https://thstore.ru/catalog/bags-and-suitcases/veloryukzaki/");
        cyclingBackpacksUrlList.getUrlList().addAll(cyclingBackpacks);
        tHStoreUrlLists.add(cyclingBackpacksUrlList);

        UrlList touristsBagUrlList = new UrlList("Туристические рюкзаки", true);
        List<String> touristsBag = new ArrayList<>();
        touristsBag.add("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-alltrail/");
        touristsBag.add("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/thule-capstone-backpacks/");
        touristsBag.add("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-guidepost/");
        touristsBag.add("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-landmark/");
        touristsBag.add("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/ryukzaki-perenoski/");
        touristsBag.add("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-stir/");
        touristsBag.add("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/backpacks-thule-versant/");
        touristsBagUrlList.getUrlList().addAll(touristsBag);
        tHStoreUrlLists.add(touristsBagUrlList);

        UrlList skiCoversUrlList = new UrlList("Чехлы для лыж", true);
        List<String> skiCovers = new ArrayList<>();
        skiCovers.add("https://thstore.ru/catalog/bags-and-suitcases/covers-for-winter/dlya-lyzh/");
        skiCoversUrlList.getUrlList().addAll(skiCovers);
        tHStoreUrlLists.add(skiCoversUrlList);

        UrlList snowboardCoversUrlList = new UrlList("Чехлы для сноубордов", true);
        List<String> snowboardCovers = new ArrayList<>();
        snowboardCovers.add("https://thstore.ru/catalog/bags-and-suitcases/covers-for-winter/dlya-snoubordov/");
        snowboardCoversUrlList.getUrlList().addAll(snowboardCovers);
        tHStoreUrlLists.add(snowboardCoversUrlList);

        UrlList backpacksForShoesUrlList = new UrlList("Рюкзаки для обуви и одежды", true);
        List<String> backpacksForShoes = new ArrayList<>();
        backpacksForShoes.add("https://thstore.ru/catalog/bags-and-suitcases/covers-for-winter/for-shoes-and-apparel/");
        backpacksForShoesUrlList.getUrlList().addAll(backpacksForShoes);
        tHStoreUrlLists.add(backpacksForShoesUrlList);

        UrlList laptopCasesUrlList = new UrlList("Чехлы-сумки для ноутбуков", true);
        List<String> laptopCases = new ArrayList<>();
        laptopCases.add("https://thstore.ru/catalog/bags-and-cases/for-laptops-and-tablets/for-laptops/");
        laptopCasesUrlList.getUrlList().addAll(laptopCases);
        tHStoreUrlLists.add(laptopCasesUrlList);

        UrlList casesForCamerasUrlList = new UrlList("Чехлы для фотоаппаратов", true);
        List<String> casesForCameras = new ArrayList<>();
        casesForCameras.add("https://thstore.ru/catalog/bags-and-cases/for-cameras/");
        casesForCamerasUrlList.getUrlList().addAll(casesForCameras);
        tHStoreUrlLists.add(casesForCamerasUrlList);

        UrlList bumpersForMacbookUrlList = new UrlList("Бампера для Macbook", true);
        List<String> bumpersForMacbook = new ArrayList<>();
        bumpersForMacbook.add("https://thstore.ru/catalog/bags-and-cases/for-laptops-and-tablets/for-tablets/");
        bumpersForMacbookUrlList.getUrlList().addAll(bumpersForMacbook);
        tHStoreUrlLists.add(bumpersForMacbookUrlList);

        UrlList iPhoneCasesUrlList = new UrlList("Чехлы для iPhone", true);
        List<String> iPhoneCases = new ArrayList<>();
        iPhoneCases.add("https://thstore.ru/catalog/bags-and-cases/for-smartphones/chekhly-dlya-iphone/");
        iPhoneCasesUrlList.getUrlList().addAll(iPhoneCases);
        tHStoreUrlLists.add(iPhoneCasesUrlList);

        UrlList samsungCasesUrlList = new UrlList("Чехлы для Samsung", true);
        List<String> samsungCases = new ArrayList<>();
        samsungCases.add("https://thstore.ru/catalog/bags-and-cases/for-smartphones/chekhly-dlya-samsung/");
        samsungCasesUrlList.getUrlList().addAll(samsungCases);
        tHStoreUrlLists.add(samsungCasesUrlList);

        UrlList backpackAccessoriesUrlList = new UrlList("Аксессуары для рюкзаков", true);
        List<String> backpackAccessories = new ArrayList<>();
        backpackAccessories.add("https://thstore.ru/catalog/bags-and-suitcases/hiking-backpacks/accessories-for-backpacks/");
        backpackAccessoriesUrlList.getUrlList().addAll(backpackAccessories);
        tHStoreUrlLists.add(backpackAccessoriesUrlList);
    }

    private void bagajnikInit() {
        UrlList carBoxesUrlList = new UrlList("Автомобильные боксы", false);
        List<String> carBoxes = new ArrayList<>();
        carBoxes.add("http://bagazhnik.ru/844109_boks_lux_flagman_370l_seryy_matovyy_1392h902h392_s_dvustor._otkr._art._844109");
        carBoxes.add("http://bagazhnik.ru/844086_boks_lux_flagman_370l_chernyy_matovyy_1392h902h392_s_dvustor._otkr._art._844086");
        carBoxes.add("http://bagazhnik.ru/694999_boks_lux_600_440l_seryy_matovyy_1600h920h400_s_dvustor._otkr._art._694999");
        carBoxes.add("http://bagazhnik.ru/694982_boks_lux_600_440l_chernyy_matovyy_1600h920h400_s_dvustor._otkr._art._694982");
        carBoxes.add("http://bagazhnik.ru/844154_boks_lux_viking_460l_seryy_matovyy_1737h812h412_s_dvustor._otkr._art._844154");
        carBoxes.add("http://bagazhnik.ru/844130_boks_lux_viking_460l_chernyy_matovyy_1737h812h412_s_dvustor._otkr._art._844130");
        carBoxes.add("http://bagazhnik.ru/844093_boks_lux_flagman_370l_chernyy_metallik_1392h902h392_s_dvustor._otkr._art._844093");
        carBoxes.add("http://bagazhnik.ru/844116_boks_lux_flagman_370l_seryy_metallik_1392h902h392_s_dvustor._otkr.._art._844116");
        carBoxes.add("http://bagazhnik.ru/844123_boks_lux_flagman_370l_belyy_glyanets_1392h902h392_s_dvustor._otkr._art._844123");
        carBoxes.add("http://bagazhnik.ru/695163_boks_lux_600_440l_seryy_metallik_1600h920h400_s_dvustor._otkr._art._695163");
        carBoxes.add("http://bagazhnik.ru/695156_boks_lux_600_440l_chernyy_metallik_1600h920h400_s_dvustor._otkr._art._695156");
        carBoxes.add("http://bagazhnik.ru/694951_boks_lux_960_480l_seryy_matovyy_1960h780h420_s_dvustor._otkr._art._694951");
        carBoxes.add("http://bagazhnik.ru/694975_boks_lux_960_480l_chernyy_matovyy_1960h780h420_s_dvustor._otkr._art._694975");
        carBoxes.add("http://bagazhnik.ru/844161_boks_lux_viking_460l_seryy_metallik_1737h812h412_s_dvustor._otkr._art._844161");
        carBoxes.add("http://bagazhnik.ru/844147_boks_lux_viking_460l_chernyy_metallik_1737h812h412_s_dvustor._otkr._art._844147");
        carBoxes.add("http://bagazhnik.ru/697136_boks_lux_600_440l_belyy_glyanets_1600h920h400_s_dvustor._otkr._art._697136");
        carBoxes.add("http://bagazhnik.ru/844178_boks_lux_viking_460l_belyy_glyanets_1737h812h412_s_dvustor._otkr._art._844178");
        carBoxes.add("http://bagazhnik.ru/695170_boks_lux_960_480l_chernyy_metallik_1960h780h420_s_dvustor._otkr._art._695170");
        carBoxes.add("http://bagazhnik.ru/695187_boks_lux_960_480l_seryy_metallik_1960h780h420_s_dvustor._otkr._art._695187");
        carBoxes.add("http://bagazhnik.ru/697624_boks_lux_960_480l_belyy_glyanets_1960h780h420_s_dvustor._otkr._art._697624");
        carBoxesUrlList.getUrlList().addAll(carBoxes);
        bagajnikUrlLists.add(carBoxesUrlList);

        UrlList bikeRackUrlList = new UrlList("Велокрепления", false);
        List<String> bikeRack = new ArrayList<>();
        bikeRack.add("http://bagazhnik.ru/691028_lux_dlya_perevozki_velosipedov_universalnoe_luxbike_1_art._691028");
        bikeRack.add("http://bagazhnik.ru/846257_velokreplenie_lux_smart_art._846257");
        bikeRack.add("http://bagazhnik.ru/846240_velokreplenie_lux_profi_art._846240");
        bikeRackUrlList.getUrlList().addAll(bikeRack);
        bagajnikUrlLists.add(bikeRackUrlList);

        UrlList skyFastenersUrlList = new UrlList("Крепления для лыж и сноубордов", false);
        List<String> skyFasteners = new ArrayList<>();
        skyFasteners.add("http://bagazhnik.ru/848510_kreplenie_dlya_perevozki_lyj_3_pary_lux_yeti");
        skyFastenersUrlList.getUrlList().addAll(skyFasteners);
        bagajnikUrlLists.add(skyFastenersUrlList);

        UrlList cargoBasketsUrlList = new UrlList("Грузовые корзины", false);
        List<String> cargoBaskets = new ArrayList<>();
        cargoBaskets.add("http://bagazhnik.ru/845205_korzina_bagajnaya_lux_rayder_1200h950mm_art._845205");
        cargoBaskets.add("http://bagazhnik.ru/845212_korzina_bagajnaya_lux_ekselent_1600h1000_art._845212");
        cargoBasketsUrlList.getUrlList().addAll(cargoBaskets);
        bagajnikUrlLists.add(cargoBasketsUrlList);

        UrlList accessoriesUrlList = new UrlList("Аксессуары", false);
        List<String> accessories = new ArrayList<>();
        accessories.add("http://bagazhnik.ru/690298_stopor_muravey_2_sht._art._690298");
        accessories.add("http://bagazhnik.ru/694494_nabor_boltov_sekretnyh_bagajnoy_sistemy_lux_50_mm_art._694494");
        accessories.add("http://bagazhnik.ru/849104_nabor_perehodnikov_dlya_ustanovki_lk_lux_yeti_na_pryamougolnye_poperechiny");
        accessories.add("http://bagazhnik.ru/843157_nabor_lichinok_s_klyuchami_bagajnoy_sistemy_lux_4_sht._art._843157");
        accessories.add("http://bagazhnik.ru/zamki_dlya_boksa_lux_2_sht_zamki_dlya_boksa_lux_2_sht");
        accessories.add("http://bagazhnik.ru/690304_kreplenie_dlya_perevozki_lodok_muravey_2_sht._art._690304");
        accessories.add("http://bagazhnik.ru/693527_stopor_dlya_fiksatsii_gruzov_lux_vysota_9_sm_komplekt_2_sht._art._693527");
        accessories.add("http://bagazhnik.ru/845588_sumka_dlya_boksa_lux");
        accessoriesUrlList.getUrlList().addAll(accessories);
        bagajnikUrlLists.add(accessoriesUrlList);
    }

    public List<UrlList> geteSAutoUrlLists() {
        return eSAutoUrlLists;
    }

    public List<UrlList> getEvrodetalUrlLists() {
        return evrodetalUrlLists;
    }

    public List<UrlList> getaTuningUrlLists() {
        return aTuningUrlLists;
    }

    public List<UrlList> gettHStoreUrlLists() {
        return tHStoreUrlLists;
    }

    public List<UrlList> getBagajnikUrlLists() {
        return bagajnikUrlLists;
    }
}
