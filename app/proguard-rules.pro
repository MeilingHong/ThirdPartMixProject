# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-dontskipnonpubliclibraryclasses
-dontpreverify
-dontoptimize
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
################   统计     ############
-keepattributes *Annotation*,EnclosingMethod,Signature
-keepclasseswithmembers class * {
   public <init>(android.content.Context, android.util.AttributeSet, int);
 }

 # Google Play Services library
-keep class * extends java.util.ListResourceBundle {
   protected Object[][] getContents();
}

 -keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
  public static final *** NULL;
 }

 -keepnames @com.google.android.gms.common.annotation.KeepName class *
 -keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
  }

 -keepnames class * implements android.os.Parcelable {
  public static final ** CREATOR;
 }
############## Android  ###########
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
-keep public class * extends android.app.Application { *; }
-dontwarn android.app.ContentProviderHolder

############## json 数据不被混淆

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep public class * implements java.io.Serializable {
    *;
}

-keep class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
}

#Otto
-keepclassmembers class ** {
	@com.squareup.otto.Subscribe public *;
	@com.squareup.otto.Produce public *;
}

############## Weibo  ###########
-keep  public class com.sina.deviceidjnisdk.DeviceId{
     *;
 }
-keepattributes InnerClasses
-keep class com.alibaba.android.vlayout.ExposeLinearLayoutManagerEx { *; }
-keep class android.support.v7.widget.RecyclerView$LayoutParams { *; }
-keep class android.support.v7.widget.RecyclerView$ViewHolder { *; }
-keep class android.support.v7.widget.ChildHelper { *; }
-keep class android.support.v7.widget.ChildHelper$Bucket { *; }
-keep class android.support.v7.widget.RecyclerView$LayoutManager { *; }

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepattributes  InnerClasses,LocalVariableTypeTable,LineNumberTable,SourceFile,LocalVariableTable,EnclosingMethod

-keep class com.sina.weibo.wcfc.sobusiness.**{*;}

-keep class com.sina.weibo.sdk.net.**{*;}
-keep class com.sina.weibo.sdk.network.**{*;}

-keep class com.sina.weibo.sdk.api.**{*;}
-keep class com.sina.weibo.sdk.auth.**{*;}
-keep class com.sina.weibo.sdk.share.**{*;}
-keep class com.sina.weibo.sdk.WbSdk{*;}

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
