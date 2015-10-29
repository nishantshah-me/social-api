# social-api

Easiest way to sign in through Facebook,Google and Twitter.

How do I use it?
---

### Setup

##### Gradle
```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'com.github.nishant-git:social-api:1.0.1'
}
```

# Basic Facebook login
It is as simple as,

```java
Social.with(this).login(new FacebookService(getResources().getString(R.string.facebook_app_id)));
```
# Logout from any screen/activity/fragment 
```java
 if(Social.logout(this)){
            Toast.makeText(LogInActivity.this,"Logged out successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LogInActivity.this,"Failed",Toast.LENGTH_SHORT).show();
        }
```
