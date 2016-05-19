# social-api

Easiest way to sign in through Facebook,Google and Twitter.
Twitter coming soon!

How do I use it?
---

### Setup

##### Gradle
```groovy
repositories {
    jcenter()
    maven { url "https://dl.bintray.com/nishant-bintray/maven" }
    maven { url 'https://maven.fabric.io/public' }
}

dependencies {
    compile 'com.github.nishant-git:social-api:1.1'
}
```
### Getting started

  First of all, you need to register you application, please check this links: [Facebook](https://developers.facebook.com/products/login),  [GooglePlus](https://developers.google.com/console/help/new/#managingprojects)
Next you need to write code as follows,

##### Basic Facebook login
It is as simple as,

```java
//for facebook
Social.with(this).login(new FacebookService("YOUR FACEBOOK APPLICATION ID");
//for G+
 Social.with(this).login(new GoogleService());
```
##### Logout from any screen/activity/fragment 
```java
 if(Social.logout(this)){
            Toast.makeText(LogInActivity.this,"Logged out successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LogInActivity.this,"Failed",Toast.LENGTH_SHORT).show();
        }
```



License
-------

    Copyright 2015 Nishant Shah

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

