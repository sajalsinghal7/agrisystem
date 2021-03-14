package com.fms.farm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.fms.farm.dao.UploadJobStatusDynamoDb;

@Service
public class FetchJobStatusDataFromDB {

	@Autowired
	public AmazonDynamoDB amazonDynamoDB;
	public List<UploadJobStatusDynamoDb> getAll() {
//		QueryRequest queryRequest = new QueryRequest().withTableName("upload_job_status");
//		UploadJobStatusDynamoDb hashEntity = new UploadJobStatusDynamoDb();
//		hashEntity.setId("1");
//		
//		DynamoDBQueryExpression<UploadJobStatusDynamoDb> queryExpression = new DynamoDBQueryExpression<UploadJobStatusDynamoDb>();
//		queryExpression.withIndexName("id");
//		return dynamoDBMapper.query(UploadJobStatusDynamoDb.class, queryExpression);
		DynamoDB db = new DynamoDB(amazonDynamoDB);
		Table table = db.getTable("upload_job_status");
		QuerySpec querySpec = new QuerySpec();
		querySpec.withKeyConditionExpression("id = :v_id");
		querySpec.withValueMap(new ValueMap().withString(":v_id", "job1"));
		ItemCollection<QueryOutcome> uploadedJobStatus = table.query(querySpec);
		Iterator<Item> iterator = uploadedJobStatus.iterator();
		List<UploadJobStatusDynamoDb> result = new ArrayList<UploadJobStatusDynamoDb>();
		while (iterator.hasNext()) {

            Map<String, Object> tmp = iterator.next().asMap();
            result.add(new UploadJobStatusDynamoDb(tmp.get("id").toString(), tmp.get("status").toString(), tmp.get("createDate").toString(), tmp.get("endDate").toString()));
        }
		return result;
		
	}
}
