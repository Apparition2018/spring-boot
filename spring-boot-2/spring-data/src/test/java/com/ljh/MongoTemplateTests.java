package com.ljh;

import com.ljh.entity.mongo.EmployeeMO;
import com.ljh.entity.mongo.ZipMO;
import com.ljh.repository.mongo.ZipRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * MongoTemplate
 *
 * @author ljh
 * created on 2022/11/30 17:17
 */
@SpringBootTest
public class MongoTemplateTests {

    @Resource
    private MongoTemplate mongoTemplate;
    @Resource(name = "myGridFsTemplate")
    private GridFsTemplate gridFsTemplate;
    @Resource
    private ZipRepository zipRepository;

    @Test
    public void testCreateCollection() {
        if (mongoTemplate.collectionExists("emp")) {
            mongoTemplate.dropCollection("emp");
        }
        mongoTemplate.createCollection("emp");
    }

    @Test
    public void testInsert() {
        EmployeeMO emp = new EmployeeMO(1, "小明", 30, 10000.00, new Date());
        // save     _id 存在时更新数据
        // insert   _id 存在时抛出异常，支持批量操作
        mongoTemplate.insert(emp);

        List<EmployeeMO> empList = Arrays.asList(
                new EmployeeMO(2, "张三", 21, 5000.00, new Date()),
                new EmployeeMO(3, "李四", 26, 8000.00, new Date()),
                new EmployeeMO(4, "王五", 22, 5000.00, new Date()),
                new EmployeeMO(5, "张龙", 28, 6000.00, new Date()),
                new EmployeeMO(6, "赵虎", 24, 7000.00, new Date()),
                new EmployeeMO(7, "赵六", 28, 12000.00, new Date())
        );
        mongoTemplate.insert(empList, EmployeeMO.class);
    }

    @Test
    public void testFind() {
        System.err.println("===== findAll =====");
        mongoTemplate.findAll(EmployeeMO.class).forEach(System.out::println);

        System.err.println("===== findById =====");
        System.out.println(mongoTemplate.findById(1, EmployeeMO.class));

        System.err.println("===== findOne =====");
        System.out.println(mongoTemplate.findOne(new Query(), EmployeeMO.class));

        System.err.println("===== salary < 4000 and salary > 1000 =====");
        mongoTemplate.find(new Query(Criteria.where("salary").gt(4000).lt(1000)), EmployeeMO.class).forEach(System.out::println);

        System.err.println("===== name like '%张%' =====");
        mongoTemplate.find(new Query(Criteria.where("name").regex("张")), EmployeeMO.class).forEach(System.out::println);

        System.err.println("===== name = '张三' or salary > 5000 =====");
        mongoTemplate.find(new Query(new Criteria().orOperator(Criteria.where("name").is("张三"), Criteria.where("salary").gt(5000))),
                EmployeeMO.class).forEach(System.out::println);

        System.err.println("===== order by salary desc limit 0, 4 =====");
        mongoTemplate.find(new Query().with(Sort.by(Sort.Order.desc("salary"))).skip(0).limit(4), EmployeeMO.class).forEach(System.out::println);
    }

    @Test
    public void testFindByJson() {
        String json = "{$or:[{age:{$gt:25}},{salary:{$gte:8000}}]}";
        BasicQuery query = new BasicQuery(json);
        mongoTemplate.find(query, EmployeeMO.class).forEach(System.out::println);
    }

    @Test
    public void testUpdate() {
        UpdateResult updateResult;

        System.err.println("===== updateFirst: update emp set salary = 10000 where salary = 13000 =====");
        updateResult = mongoTemplate.updateFirst(new Query(Criteria.where("salary").gte(10000)), new Update().set("salary", 13000), EmployeeMO.class);
        // 返回修改的文档数
        System.out.println(updateResult.getMatchedCount());

        System.err.println("===== updateMulti: update emp set salary = 10000 where salary = 14000 =====");
        updateResult = mongoTemplate.updateMulti(new Query(Criteria.where("salary").gte(10000)), new Update().set("salary", 14000), EmployeeMO.class);
        System.out.println(updateResult.getMatchedCount());

        System.err.println("===== upsert: 没有匹配的文档，则新插入文档 =====");
        updateResult = mongoTemplate.upsert(new Query(Criteria.where("salary").gte(15000)),
                new Update().set("salary", 14000).set("username", "孙七").set("age", 30).set("birthday", new Date())
                        .setOnInsert("id", 11), EmployeeMO.class);
        System.out.println(updateResult.getMatchedCount());
    }

    @Test
    public void testDelete() {
        mongoTemplate.remove(new Query(Criteria.where("salary").gte(10000)), EmployeeMO.class);

        mongoTemplate.dropCollection("emp");
    }

    @Test
    public void testAggregate() {
        // 返回人口超过1000万的州
        // db.zip.aggregate([
        //     { $group: { _id: "$state", totalPop: { $sum: "$pop" } }, },
        //     { $match: { totalPop: { $gt: 1000 * 10000 } } },
        //     { $sort: { totalPop: -1 } }
        // ])
        GroupOperation groupOperation = Aggregation.group("state").sum("pop").as("totalPop");
        MatchOperation matchOperation = Aggregation.match(Criteria.where("totalPop").gt(1000 * 10000));
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "totalPop");
        this.print(groupOperation, matchOperation, sortOperation);
    }

    @Test
    public void testAggregate2() {
        // 返回各州平均城市人口
        // db.zip.aggregate([
        //     { $group: { _id: { state: "$state", city: "$city" }, cityPop: { $sum: "$pop" } } },
        //     { $group: { _id: "$_id.state", avgCityPop: { $avg: "$cityPop" } } },
        //     { $sort: { avgCityPop: -1 } }
        // ])
        GroupOperation groupOperation = Aggregation.group("state", "city").sum("pop").as("cityPop");
        GroupOperation groupOperation2 = Aggregation.group("_id.state").avg("cityPop").as("avgCityPop");
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "avgCityPop");
        this.print(groupOperation, groupOperation2, sortOperation);
    }

    @Test
    public void testAggregate3() {
        // 按州返回人口最大和最小的城市
        // db.zip.aggregate([
        //     { $group: { _id: { state: "$state", city: "$city" }, cityPop: { $sum: "$pop" } } },
        //     { $sort: { cityPop: 1 } },
        //     {
        //         $group: {
        //             _id: "$_id.state",
        //             biggestCity: { $last: "$_id.city" },
        //             biggestPop: { $last: "$cityPop" },
        //             smallestCity: { $first: "$_id.city" },
        //             smallestPop: { $first: "$cityPop" }
        //         }
        //     },
        //     {
        //         $project: {
        //             _id: 0,
        //             state: "$_id",
        //             biggestCity: { name: "$biggestCity", pop: "$biggestPop" },
        //             smallestCity: { name: "$smallestCity", pop: "$smallestPop" }
        //         }
        //     },
        //     { $sort: { state: 1 } }
        // ])
        GroupOperation groupOperation = Aggregation.group("state", "city").sum("pop").as("cityPop");
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.ASC, "cityPop");
        GroupOperation groupOperation2 = Aggregation.group("_id.state")
                .last("_id.city").as("biggestCity")
                .last("cityPop").as("biggestPop")
                .first("_id.city").as("smallestCity")
                .first("cityPop").as("smallestPop");
        ProjectionOperation projectionOperation = Aggregation.project("state", "biggestCity", "smallestCity")
                .andExclude("_id")
                .and("_id").as("state")
                .andExpression("{ name: \"$biggestCity\", pop: \"$biggestPop\" }").as("biggestCity")
                .andExpression("{ name: \"$smallestCity\", pop: \"$smallestPop\" }").as("smallestCity");
        SortOperation sortOperation2 = Aggregation.sort(Sort.Direction.ASC, "state");
        this.print(groupOperation, sortOperation, groupOperation2, projectionOperation, sortOperation2);
    }

    private void print(AggregationOperation... operations) {
        TypedAggregation<ZipMO> typedAggregation = Aggregation.newAggregation(ZipMO.class, operations);
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(typedAggregation, Map.class);
        aggregationResults.getMappedResults().forEach(System.out::println);
    }

    @Test
    public void testGridFS() throws IOException {
        /* https://www.mongodb.com/docs/drivers/java/sync/v4.8/fundamentals/gridfs/ */
        MongoDatabase db = mongoTemplate.getDb();
        GridFSBucket gridFSBucket = GridFSBuckets.create(db, "file");
        File file = new File("C:/Users/HP/Desktop/img/heart.png");
        String fileMd5 = DigestUtils.md5DigestAsHex(Files.newInputStream(file.toPath()));
        // 根据 md5 查找并删除文件
        gridFSBucket.find(Filters.eq("metadata.md5", fileMd5)).forEach(gridFSFile -> gridFSBucket.delete(new ObjectId(String.valueOf(gridFSFile.getObjectId()))));
        GridFSUploadOptions options = new GridFSUploadOptions()
                .chunkSizeBytes(1048576)
                // 元数据
                .metadata(new Document("type", "image").append("content_type", "image/png").append("md5", fileMd5));
        // 上传文件
        ObjectId objectId = gridFSBucket.uploadFromStream(file.getName(), Files.newInputStream(file.toPath()), options);
        System.out.println(objectId.toHexString());
    }

    @Test
    public void testGridFS2() throws IOException {
        File file = new File("C:/Users/HP/Desktop/img/heart.png");
        String fileMd5 = DigestUtils.md5DigestAsHex(Files.newInputStream(file.toPath()));
        // 根据 md5 查找文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("metadata.md5").is(fileMd5)));
        if (gridFSFile != null) {
            // 删除文件
            gridFsTemplate.delete(new Query(Criteria.where("_id").is(gridFSFile.getObjectId())));
        }
        BasicDBObject metadata = new BasicDBObject();
        metadata.put("type", "image");
        metadata.put("content_type", "image/png");
        metadata.put("md5", fileMd5);
        // 上传文件
        gridFsTemplate.store(Files.newInputStream(file.toPath()), file.getName(), metadata);
    }

    @Test
    public void testRepository() {
        zipRepository.findAll().forEach(System.out::println);
    }
}
