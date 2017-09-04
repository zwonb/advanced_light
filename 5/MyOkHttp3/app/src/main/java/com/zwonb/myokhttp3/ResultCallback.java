package com.zwonb.myokhttp3;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zyb on 2017/8/30.
 */

public abstract class ResultCallback {

    abstract void onError(Request request, Exception e);

    abstract void onResponse(Response response) throws IOException;
}
