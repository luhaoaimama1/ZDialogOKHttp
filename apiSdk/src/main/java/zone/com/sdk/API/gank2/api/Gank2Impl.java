/*
 * Copyright 2017 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2017-03-08 01:01:18
 *
 * GitHub:  https://github.com/GcsSloop
 * Website: http://www.gcssloop.com
 * Weibo:   http://weibo.com/GcsSloop
 */

package zone.com.sdk.API.gank2.api;
import zone.com.okhttplib.java.callwrapper.DialogCall;
import zone.com.sdk.API.gank.bean.MeiZiData;
import zone.com.sdk.base.BaseImpl;

//ListHelper.bindEngine()

/**
 *GankImpl.pop(listener{
 *
 *})
 *
 * getPics(String limit, String pageNumber).pop/dialog/popListener().enquene(callback());
 */
public class Gank2Impl extends BaseImpl<Gank2Service> implements Gank2API {

    //第三个参数主要是为了 APIController2类的测试
    @Override
    public DialogCall<MeiZiData> getPics(String limit, String pageNumber, int noUse) {
        return dialogWrapper(mService.getPics(limit,pageNumber));
    }
}
