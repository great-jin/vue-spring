package xyz.ibudai.utils;

import io.minio.*;
import java.util.*;
import java.io.InputStream;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import xyz.ibudai.entity.model.MinioRespond;

@Slf4j
@Component
public class MinioUtil {

    @Autowired
    private MinioClient client;

    /**
     * 判断 bucket 是否存在
     *
     * @param bucketName
     */
    public boolean bucketExist(String bucketName) throws Exception {
        return client.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
    }

    /**
     * 创建 bucket
     *
     * @param bucketName
     */
    public void createBucket(String bucketName) throws Exception {
        if (!bucketExist(bucketName)) {
            client.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
    }

    /**
     * 删除 bucket
     *
     * @param bucketName
     */
    public void removeBucket(String bucketName) throws Exception {
        if (bucketExist(bucketName)) {
            client.removeBucket(RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
    }

    /**
     * 根据 bucketName 获取信息
     *
     * @param bucketName bucket名称
     */
    public Optional<Bucket> getBucket(String bucketName) throws Exception {
        return client.listBuckets().stream()
                .filter(b -> b.name().equals(bucketName))
                .findFirst();
    }

    /**
     * 获取全部 bucket
     *
     * @return
     */
    public List<Bucket> getAllBuckets() throws Exception {
        return client.listBuckets();
    }

    /**
     * 上传文件
     */
    public MinioRespond uploadFile(MultipartFile file, String bucketName) throws Exception {
        // 拼接文件名： <UUID>_<FileName>.Suffix
        String originName = file.getOriginalFilename();
        StringBuilder fileName = new StringBuilder();
        fileName.append(UUID.randomUUID());
        fileName.append("_");
        fileName.append(originName);

        ObjectWriteResponse objectWriteResponse = null;

        // 上传
        objectWriteResponse = client.putObject(
                PutObjectArgs.builder().bucket(bucketName)
                        .object(fileName.toString())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .build());

        MinioRespond respond = new MinioRespond(originName,
                fileName.toString(), objectWriteResponse);
        return respond;
    }

    /**
     * 获取⽂件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @return ⼆进制流
     */
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return client.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 上传⽂件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @param stream     ⽂件流
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws
            Exception {
        client.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, stream.available(), -1)
                .contentType(objectName.substring(objectName.lastIndexOf(".")))
                .build());
    }

    /**
     * 上传⽂件
     *
     * @param bucketName  bucket名称
     * @param objectName  ⽂件名称
     * @param stream      ⽂件流
     * @param size        ⼤⼩
     * @param contextType 类型
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long
            size, String contextType) throws Exception {
        client.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, size, -1)
                .contentType(contextType)
                .build());
    }

    /**
     * 获取⽂件信息
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     */
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) throws Exception {
        return client.statObject(StatObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 删除⽂件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        client.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 获取⽂件外链
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    public String getObjectURL(String bucketName, String objectName, Integer expires) throws Exception {
        return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .expiry(expires)
                .build());
    }

}