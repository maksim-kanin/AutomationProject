package com.tricentis.demowebshop.utils.credentials;

import com.tricentis.demowebshop.utils.credentials.beans.User;

public interface CredentialsProvider {
    User getUser(String userRole);
}
