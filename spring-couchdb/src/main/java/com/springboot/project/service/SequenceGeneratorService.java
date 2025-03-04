package com.springboot.project.service;

import com.couchbase.client.core.deps.org.xbill.DNS.Update;
import com.springboot.project.model.DatabaseSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SequenceGeneratorService {

    private final CouchbaseOperations couchbaseOperations;

    public long generateVersionSequence(String seqName) {
        if (Objects.isNull(seqName)) 
            throw new IllegalArgumentException("seqName must not be null!");
        DatabaseSequence counter = couchbaseOperations.getConsistency(
            Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("version",1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getVersion() : 1;
    }


}