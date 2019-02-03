/**
 * Copyright 2013 Ognyan Bankov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mesbahsoft.namazghaza;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Demonstrates how to execute <code>JsonObjectRequest</code>
 * @author Ognyan Bankov
 *
 */
public class JsonRequest2 extends Activity {
    private TextView mTvDesc;
    private TextView mTvUrlUpdate;
    private TextView mTvVerNo;
    public String urlurl1="http://echo.jsontest.com/key/value/one/two";
    public String urlurl2="http://mvc.learnkey.ir/esl/getmobilenumberbyUseridDeviceID/saber/s54613164sf54/";
    public String urlurl3="http://mvc.learnkey.ir/esl/SetUserIDbyDeviceID/saber/s54613164sf54/";
    public String urlurl4="http://mvc.learnkey.ir/api/getNewVersion/namazghaza/1/1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.act__json_request);

        mTvDesc = (TextView) findViewById(R.id.tv_description);
        mTvUrlUpdate = (TextView) findViewById(R.id.tv_url_apk);
        mTvVerNo = (TextView) findViewById(R.id.tv_version_number);



        Button btnJsonRequest = (Button) findViewById(R.id.btn_json_request);
        btnJsonRequest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = com.mesbahsoft.namazghaza.app.MyVolley.getRequestQueue();
                
                JsonObjectRequest myReq = new JsonObjectRequest(Method.GET, 
                                                        urlurl4,
                                                        null,
                                                        createMyReqSuccessListener(),
                                                        createMyReqErrorListener());

                queue.add(myReq);
            }
        });
    }
    
 
    private Response.Listener<JSONObject> createMyReqSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    mTvDesc.setText(response.getString("description"));
                    mTvUrlUpdate.setText(response.getString("url_apk"));
                    mTvVerNo.setText(response.getString("version_number"));
                } catch (JSONException e) {
                    mTvDesc.setText("Parse error");
                }
            }
        };
    }
    
    
    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTvDesc.setText(error.getMessage());
            }
        };
    }
}
