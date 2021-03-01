package utils;

import com.opencsv.CSVWriter;
import model.Product;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Utils {
    public static void downloadImg(Product product) throws IOException {
        BufferedImage image = null;
        List<String> photosName = new ArrayList<>();
        int i = 1;
        for (String urlPic : product.getPhotosUrl()) {
            if (isRussian(urlPic) || urlPic.equals("https:") || urlPic.equals(product.getSiteUrl())) {
                continue;
            }
            URL url = new URL(urlPic);
            try {
                URLConnection connection = url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                connection.connect();
                image = ImageIO.read(connection.getInputStream());
            }
            catch (IOException e) {
                continue;
            }

            if (product.getCharacteristics().get("Артикул:") == null || product.getCharacteristics().get("Артикул:").equals("")) {
                product.getCharacteristics().put("Артикул:", "01-" + String.valueOf(nextInt()));
            }

            String imgName = product.getCharacteristics().get("Артикул:") + "_" + i;
            File catFolder = new File("C:\\esAutoImg\\" + product.getCategory());
            if (!catFolder.exists()) {
                catFolder.mkdir();
            }
            File goodsFolder = new File(catFolder + File.separator + validFoldName(product.getCharacteristics().get("Артикул:")));
            if (!goodsFolder.exists()) {
                goodsFolder.mkdir();
            }

            String fmt = urlPic.substring(urlPic.lastIndexOf(".")+1);
            if (fmt.equals("") || fmt.length() > 4) {
                fmt = "jpg";
            }

            ImageIO.write(image, fmt, new File(goodsFolder + File.separator + validFoldName(imgName) + "." + fmt));
            photosName.add(imgName + "." + fmt);
            ++i;
        }
        product.setPhotosName(photosName);
    }

    public static void downloadPdf(Product product, String urlPdf) throws IOException {
        if (urlPdf.contains(" ")) {
            urlPdf = urlPdf.replace(" ", "%20");
        }
//        if (isRussian(urlPdf)) {
//            return;
//        }
//        URL url = new URL("https://es-auto.ru" + urlPdf);
        URL url = new URL(urlPdf);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();

        File catFolder = new File("C:\\esAutoPDF\\" + product.getCategory());
        if (!catFolder.exists()) {
            catFolder.mkdir();
        }
        File goodsFolder = new File(catFolder + File.separator + validFoldName(product.getShortTitle()) + " (" + product.getCharacteristics().get("Артикул:") + ")");
        if (!goodsFolder.exists()) {
            goodsFolder.mkdir();
        }
        String fileName = goodsFolder.getAbsolutePath() + File.separator + product.getCharacteristics().get("Артикул:") + ".pdf";
        ReadableByteChannel readableByteChannel = null;
        FileChannel fileChannel = null;
        FileOutputStream fileOutputStream = null;
        try {
            readableByteChannel = Channels.newChannel(connection.getInputStream());
            if (!new File(fileName).exists()) {
                fileOutputStream = new FileOutputStream(fileName);
                fileChannel = fileOutputStream.getChannel();
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }
        } finally {
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (readableByteChannel != null) {
                readableByteChannel.close();
            }
        }
    }

    public static  String trimNew(String text) {
        if (text.toLowerCase().endsWith(". new")) {
            return text.substring(0, text.length()-5);
        } else {
            return text;
        }
    }

    public static  String findArticle(List<Product> productList, String shortTitle) {
        for (Product product : productList) {
            if (product.getShortTitle().equals(shortTitle)) {
                return product.getCharacteristics().get("Артикул:");
            }
        }
        return shortTitle;
    }

    public static  String filterSeoUrl(String text) {
        text = text.replaceAll(" ", "-");
        text = text.replaceAll("'", "");
        text = text.replaceAll("ʺ", "");
        text = text.replaceAll("`", "");
        text = text.replaceAll(",", "");
        text = text.replaceAll("ʹ", "");
        text = text.replaceAll("=", "-");
        text = text.replaceAll("\\(", "");
        text = text.replaceAll("\\)", "");
        text = text.replaceAll("\"", "");
        text = text.replaceAll("/", "-");
        text = text.replaceAll("\\\\", "-");
        text = text.replaceAll("·", "");
        text = text.replaceAll("\\.", "");
        text = text.replaceAll("Ё", "o");
        text = text.replaceAll("ë", "o");
        text = text.replaceAll("Ë", "O");
        return text.toLowerCase();
    }

    public static  void writeCsv(List<Product> products) throws IOException {
        String csv = "C:\\esAutoCSV\\data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv), ';', '"');
        String fullCsv = "C:\\esAutoCSV\\fullData.csv";
        CSVWriter writer1 = new CSVWriter(new FileWriter(fullCsv), ';', '"');
        for (Product product : products) {
            String imgPath = "";
            if (product.getPhotosName().size() > 0) {
                imgPath = product.getPhotosName().get(0);
            }
            String [] prod = ("" + "%" +
                    product.getTitle() + "%" +
                    product.getCharacteristics().get("Артикул:") + "%" +
                    product.getCharacteristics().get("Артикул:") + "%" +
                    product.getManufacturer() + "%" +
                    product.getPrice() + "%" +
                    "20" + "%" +
                    "Купить " + product.getTitle() + " в интернет магазине berivdorogu.ru" + "%" +
                    "" + "%" +
                    "✔ " + product.getTitle() + "✈ Быстрая доставка по всей России ☺ Низкие цены ★ Официальная гарантия ✔ Заказывайте у нас!" + "%" +
                    product.getDescription() + "%" +
                    "https://berivdorogu.ru/image/catalog/product/" + imgPath + "%" +
                    "1" + "%" +
                    "1" + "%" +
                    product.getSeoUrl() + "%" +
                    "" + "%" +
                    "" + "%" +
                    getCharacters(product) + "%" +
                    getImagesPath(product) + "%" +
                    "").split("%");
            writer.writeNext(prod);

            String [] prodFullInfo = (
                            product.getCategory() + "%" +
                            product.getTitle() + "%" +
                            product.getShortTitle() + "%" +
                            product.getCharacteristics().get("Артикул:") + "%" +
                            product.getManufacturer() + "%" +
                            product.getCountry() + "%" +
                            product.getPrice() + "%" +
                            product.getVideoUrl() + "%" +
                            product.getSeoUrl() + "%" +
                            getAuto(product) + "%" +
                            product.getDescription() + "%" +
                            "Купить " + product.getTitle() + " в интернет магазине berivdorogu.ru" + "%" +
                            "✔ " + product.getTitle() + " ✈ Быстрая доставка по всей России ☺ Низкие цены ★ Официальная гарантия ✔ Заказывайте у нас!" + "%" +
                            getCharacters(product) + "%" +
                            "https://berivdorogu.ru/image/catalog/product/" + imgPath + "%" +
                            getImagesPath(product) + "%" +
                            product.getBaseUrl() + "%" +
                                    product.getFunction())
                    .split("%");
            writer1.writeNext(prodFullInfo);
        }
        writer.close();
        writer1.close();
    }

    private static String getCharacters(Product product) {
        StringBuilder characters = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : product.getCharacteristics().entrySet()) {
            characters.append("Характеристики|").append(stringStringEntry.getKey()).append("|").append(stringStringEntry.getValue()).append('\n');
        }
        return characters.toString();
    }

    private static String getImagesPath(Product product) {
        StringBuilder imagesPath = new StringBuilder();
        if (product.getPhotosName().size() < 2) {
            return "";
        }
        for (int i = 1; i < product.getPhotosName().size(); i++) {
            imagesPath.append("https://berivdorogu.ru/image/catalog/product/").append(product.getPhotosName().get(i)).append(",");
        }
        if (imagesPath.length() > 1) {
            return imagesPath.substring(0, imagesPath.length() - 1);
        } else
        {
            return "";
        }
    }

    private static String getAuto(Product product) {
        StringBuilder autos = new StringBuilder();
        if (product.getAutos() == null || product.getAutos().size() == 0) {
            return "";
        }
        for (String auto : product.getAutos()) {
            autos.append(auto).append(",").append('\n');
        }
        return autos.substring(0, autos.length() - 1);
    }

    private static boolean isRussian(String str)
    {
        char[] chr = str.toCharArray();
        for (int i = 0; i < chr.length; i++)
        {
            if (chr[i] >= 'А' && chr[i] <= 'я')
                return true;
        }
        return false;
    }

    private static String validFoldName(String folder) {
        folder = folder.replaceAll("\\*", " ");
        folder = folder.replaceAll("\\|", " ");
        folder = folder.replaceAll(":", " ");
        folder = folder.replaceAll("\"", " ");
        folder = folder.replaceAll("<", " ");
        folder = folder.replaceAll(">", " ");
        folder = folder.replaceAll("\\?", " ");
        folder = folder.replaceAll("/", "_");
        folder = folder.replaceAll("\\)", " ");
        return folder;
    }

    private static int nextInt() {
        Random random = new Random();
        return random.nextInt((9999 - 1000) + 1) + 1000;
    }
}
