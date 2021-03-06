package com.example.escosta.randomuser.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.escosta.randomuser.model.DataUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by escosta on 14/05/2017.
 */

public class MyService {

    public DataUser getInformation(String end){

        DataUser response;
        String json = Network.getJson(end);

        Log.i("Result", json);

        response = JSON.parseObject(json, DataUser.class);

        response.getResults().get(0).setPhoto(downloadImage(response.getResults().get(0).getPicture().getLarge().toString()));

        return response;
    }
    //downloadImage
    private Bitmap downloadImage(String url) {
        try {
            URL address;
            InputStream inputStream;
            Bitmap image;
            address = new URL(url);
            inputStream = address.openStream();
            image = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return image;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
