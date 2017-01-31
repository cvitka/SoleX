package hr.foi.air.solex.models;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**Definiranje Retrofita  */
public abstract class WebServiceCommunicator {
    protected Retrofit retrofit;
    private final String baseUrl = "http://barka.foi.hr/WebDiP/2015_projekti/WebDiP2015x008/";

    protected void initRetrofit(){
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
