package edu.au.cc.gallery.tools.UserAdmin;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;



public class S3 {

      private static final Region region = Region.US_EAST_2;

      private static S3Client client;
   
      public void connect() {
         client = S3Client.builder().region(region).build();
         // System.out.println("Connection successful!");
      }

      public void createBucket(final String bucketName) {
         final CreateBucketRequest createBucketRequest = CreateBucketRequest
                  .builder()
                  .bucket(bucketName)
                  .createBucketConfiguration(CreateBucketConfiguration.builder()
                           .locationConstraint(region.id())
                           .build())
                           .build();
         client.createBucket(createBucketRequest);
      }

      public void putObject(String bucketName, String key, String value) {
         PutObjectRequest por = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();
         client.putObject(por, RequestBody.fromString(value));
      }
   

      public static void demo() {
         final String bucketName = "edu.au.cc.image-gallery.s3.lac0084";
         S3 client = new S3();
         client.connect();
         client.putObject(bucketName, "key", "createSampleFile");         
      }

   
}