# AWS Security Token Service
  * How to use AWS security token service to create temporary security credentials with Spring Boot and Java app.

## Steps to create/use STS
  * Configure AWS Credentials.
  * Create AwsSessionCredentials bean.
  * Inject/Autowire AwsSessionCredentials Bean to instantiate Amazon Services.
  * Configure the properties in application.properties.
  * Make sure you create bucket in S3 console and provide same name in application.properties.
  ```
  aws.s3.data.bucket = sts-messages-2021
  ```
  * Make sure you create message.txt with some text message at some location e.g. C:\\temp\\message.txt.
  * Run applications to use Temporary Security Credentials t upload file to s3 Bucket.
