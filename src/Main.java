
public class Main {

    public static void main(String[] args){

        ViaCepAPI api = new ViaCepAPI();

        try {
            System.out.println(api.buscarCEP("58278000").getLocalidade());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
