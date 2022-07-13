package com.brainupgrade.aws.sts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;

@Configuration
public class STSAppConfig {
  private static final Integer TEMPORARY_STS_CREDENTIALS_DURATION_DEFAULT = 7200;
  @Value("${aws.region}") 
  private String awsRegion;
  @Value("${aws.s3.data.bucket}") 
  private String awsS3DataBucket;
  @Value("${aws.temporary.credentials.validity.duration}") 
  String credentialsValidityDuration;

  @Bean(name = "awsRegion")
  public Region awsRegion() {
      return Region.getRegion(Regions.fromName(awsRegion));
  }

  @Bean(name = "sessionCredentials")
  public AwsSessionCredentials sessionCredentials() {
      //
      // Create an instance of AWSSecurityTokenServiceClient
      //
      AWSSecurityTokenServiceClient stsClient = (AWSSecurityTokenServiceClient) AWSSecurityTokenServiceClientBuilder.defaultClient();
      //
      // Get session token request object; Set credentials validity duration
      //
      GetSessionTokenRequest sessionTokenRequest = new GetSessionTokenRequest();
      if(this.credentialsValidityDuration == null || this.credentialsValidityDuration.trim().equals("")) {
          sessionTokenRequest.setDurationSeconds(TEMPORARY_STS_CREDENTIALS_DURATION_DEFAULT);
      } else {
          sessionTokenRequest.setDurationSeconds(Integer.parseInt(this.credentialsValidityDuration));
      }
      //
      // Create an instance of GetSessionTokenResult using session token object
      //
      GetSessionTokenResult sessionTokenResult =
              stsClient.getSessionToken(sessionTokenRequest);
      Credentials sessionCreds = sessionTokenResult.getCredentials();
      //
      // Create an instance of BasicSessionCredentials
      //       

      AwsSessionCredentials awsSessionCredentials = AwsSessionCredentials.create(sessionCreds.getAccessKeyId(),
          sessionCreds.getSecretAccessKey(), 
          sessionCreds.getSessionToken());
      return awsSessionCredentials;
  }  
  @Bean(name = "awsS3DataBucket")
  public String awsS3DataBucket() {
      return awsS3DataBucket;
  }  
}
