package com.Spring.SpringBoot.configuration;
/*
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;*/

import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
/*

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKeyId;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretAccessKey;
    @Value("${cloud.aws.region.static}")
    private String region;

     @Bean
     public AmazonS3 getAmazonS3Cient() {
         final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
         // Get AmazonS3 client and return the s3Client object.
         return AmazonS3ClientBuilder
                 .standard()
                 .withRegion(Regions.fromName(region))
                 .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                 .build();
     }*/
}
