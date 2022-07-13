package com.brainupgrade.aws.sts;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.brainupgrade.aws.sts.storage.AWSCloudStorageS3;

@SpringBootApplication
public class SpringBootAwsStsApplication implements CommandLineRunner{

  @Autowired
  AWSCloudStorageS3 awsCloudStorageS3;

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(SpringBootAwsStsApplication.class);
    springApplication.run(args);
  }
  @Override
  public void run(String...strings) throws IOException{
    System.out.println("uploading file to bucket");
    awsCloudStorageS3.uploadFile("message.txt", "C:\\temp\\message.txt");
    System.out.println("file uploaded to bucket..complete");
  }
}
