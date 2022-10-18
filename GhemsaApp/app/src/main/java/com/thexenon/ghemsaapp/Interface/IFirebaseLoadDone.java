package com.thexenon.ghemsaapp.Interface;


import com.thexenon.ghemsaapp.models.Model;

import java.util.List;

public interface IFirebaseLoadDone {

     void onFirebaseLoadSuccess(List<Model> imgList);
     void onFirebaseLoadFailed(String message);
}
