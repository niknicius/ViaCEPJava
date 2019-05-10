import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *  API para Obtenção de dados baseados em um CEP específico
 */

public class ViaCepAPI {

    private String urlBase = "https://viacep.com.br/ws";

    public CEP buscarCEP(String cep) throws Exception {

        // Remove tudo que não for numero do CEP
        cep = cep.replaceAll("[^0-9]", "");

        String url = this.urlBase + '/' + cep + "/json";

        JSONObject jsonObject = new JSONObject(this.requisitar(url));

        if(!jsonObject.has("erro")) {
            CEP cepRetorno = new CEP(
                    jsonObject.getString("cep"),
                    jsonObject.getString("logradouro"),
                    jsonObject.getString("complemento"),
                    jsonObject.getString("bairro"),
                    jsonObject.getString("localidade"),
                    jsonObject.getString("uf"),
                    jsonObject.getString("unidade"),
                    jsonObject.getString("ibge"),
                    jsonObject.getString("gia")

            );
            return cepRetorno;
        }else{
            throw new Exception("CEP inválido!");
        }
    }

    /**
     * Cria uma requisição HTTP com o Verbo GET
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
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
