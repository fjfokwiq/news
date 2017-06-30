package com.example.fjfokwiq.news.utlis;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.fjfokwiq.news.MyApplication;
import com.example.fjfokwiq.news.bean.ImageMessage;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class ImageScanner {

    private volatile static ImageScanner instance;

    public static ImageScanner getInstance(){
        if (instance == null) {
            synchronized (ImageScanner.class) {
                if (instance == null) {
                    instance = new ImageScanner();
                }
            }
        }
        return instance;
    }
/*
* 图片扫描
* */
    public void scanner(final onScannerSucceed listener) {
        final List<ImageMessage> images = new ArrayList<>();
        cursorObservable()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<Cursor, List<ImageMessage>>() {
                    @Override
                    public List<ImageMessage> apply(@NonNull Cursor cursor) throws Exception {
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        images.add(new ImageMessage(path, name,false));
                        return images;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ImageMessage>>() {
                    @Override
                    public void accept(@NonNull List<ImageMessage> imageMessages) throws Exception {
                        listener.onSucceed(imageMessages);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

    }

    private Observable<Cursor> cursorObservable() {
        return Observable.create(new ObservableOnSubscribe<Cursor>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Cursor> e) throws Exception {
                Cursor cursor = null;
                try {
                    cursor = getCursor();
                    while (cursor.moveToNext()) {
                        e.onNext(cursor);

                    }
                    e.onComplete();
                } catch (Exception exception) {
                    e.onError(exception);
                } finally {
                    if (cursor != null)
                        cursor.close();
                }
            }
        });
    }

    private Cursor getCursor() {
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver resolver = MyApplication.context.getContentResolver();

        return resolver.query(imageUri,
                new String[]{MediaStore.Images.Media.DATA,
                        MediaStore.Images.Media.DISPLAY_NAME},
                null, null, null);
    }

    public interface onScannerSucceed {
        void onSucceed(List<ImageMessage> message);

    }
}
