import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ViaCepAPI {

    private String urlBase = "https://viacep.com.br/ws/";

    public String buscarCEP(String cep){

        String url = this.urlBase + '/' + cep + "/json";

        return this.requisitar(url);
    }

    /**
     * @param urlRequisicao Url alvo da requisição
     * @return String contendo o JSON de retorno
     */
    public String requisitar(String urlRequisicao){

        StringBuilder resultado = new StringBuilder();
        try {
            URL url = new URL(urlRequisicao);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String linha;
            while ((linha = rd.readLine()) != null) {
                resultado.append(linha);
            }

            return resultado.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
