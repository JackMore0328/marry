/*
 * Project Name: dctp
 * File Name: PasswordUtil.java
 * Class Name: PasswordUtil
 *
 * Copyright 2014 Hengtian Software Inc
 *
 * Licensed under the Hengtiansoft
 *
 * http://www.hengtiansoft.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.door.match.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordUtil {
    private static final String DEFAULT_SALT = "match201820182018match";

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new BigInteger(1, bs).toString(16);
            //return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

    public static String MD5Salt(String password) {
        return md5Hex(md5Hex(password) + DEFAULT_SALT);
    }

    public static String MD5Salt(String password, String salt) {
        String psalt = md5Hex(password) + salt;
        return md5Hex(psalt);
    }


    public static  void main(String[] ar){
        String admin = MD5Salt("admin");
        System.out.println("admin---"+admin);
    }
}
