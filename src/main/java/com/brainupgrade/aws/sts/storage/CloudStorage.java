package com.brainupgrade.aws.sts.storage;

import java.io.IOException;
import java.io.InputStream;

public interface CloudStorage {
  void uploadFile(String keyName, String filePath);
  String uploadAudioStream(String keyName, InputStream inputStream) throws IOException;
}
