package com.brainupgrade.aws.sts.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amazonaws.regions.Region;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component("awsCloudStorageS3")
public class AWSCloudStorageS3 implements CloudStorage {
  public static final String S3_BASE_URL = "http://s3.amazonaws.com/";

  @Autowired
  String awsS3DataBucket;

  private S3Client s3Client;

  public AWSCloudStorageS3(@Autowired Region awsRegion,
      @Autowired AwsSessionCredentials sessionCredentials) {
    s3Client = S3Client.builder().region(software.amazon.awssdk.regions.Region.US_EAST_1)
        .credentialsProvider(StaticCredentialsProvider.create(sessionCredentials)).build();
  }

  @Override
  public void uploadFile(String keyName, String filePath) {
    s3Client.putObject(PutObjectRequest.builder().bucket(awsS3DataBucket).key(keyName).build(),
        Path.of(filePath));
  }

  @Override
  public String uploadAudioStream(String keyName, InputStream inputStream) throws IOException {
    return null;
  }

}
