package edu.au.cc.gallery.tools.UserAdmin;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public final class Secrets {

    private final SecretsManagerClient sm;
    public Region region = Region.US_EAST_2;



    public Secrets() {
        this.sm = SecretsManagerClient.builder().region(region).build();
    }

    public String getSecretString(String secretId) {
        GetSecretValueRequest req = GetSecretValueRequest.builder().secretId(secretId).build();
        GetSecretValueResponse secret = this.sm.getSecretValue(req);

        return secret.secretString();
    }

}