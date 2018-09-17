package cn.zcbdqn.commoninventory.service.impl;


import android.util.Log;

import java.io.IOException;

import cn.zcbdqn.commoninventory.context.MyApplication;
import cn.zcbdqn.commoninventory.entity.NetworkConfig;
import cn.zcbdqn.commoninventory.entity.RfidGoodsAllocation;
import cn.zcbdqn.commoninventory.service.RfidGoodsAllocationService;
import cn.zcbdqn.commoninventory.utils.GsonUtil;
import cn.zcbdqn.commoninventory.utils.NetworkConfigConstant;
import cn.zcbdqn.commoninventory.utils.OkHttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gumuyun on 2018/9/15.
 */

public class RfidGoodsAllocationServiceImpl implements RfidGoodsAllocationService {
    @Override
    public void addRfidGoodsAllocation(RfidGoodsAllocation rfidGoodsAllocation) {

        NetworkConfig networkConfig = (NetworkConfig) MyApplication.applicationMap.get("networkConfig");
        String url=networkConfig.toUrl()+ NetworkConfigConstant.ADD_GOODS_ALLOCATION;

        String json = GsonUtil.object2Json(rfidGoodsAllocation);

        RequestBody requestBody=RequestBody.create(NetworkConfigConstant.JSON,json);

        OkHttpUtil.sendOkHttpRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("gumy","Add GoodsAllocation Fail with:"+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
