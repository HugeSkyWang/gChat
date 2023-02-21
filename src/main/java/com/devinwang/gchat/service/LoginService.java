package com.devinwang.gchat.service;

import java.util.Map;

public interface LoginService {
    Map login(String encryptedData, String iv, String code);
}
