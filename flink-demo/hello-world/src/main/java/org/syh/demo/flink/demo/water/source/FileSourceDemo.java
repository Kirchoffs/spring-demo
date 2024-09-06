package org.syh.demo.flink.demo.water.source;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FileSourceDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        FileSource<String> fileSource = FileSource
            .forRecordStreamFormat(new TextLineInputFormat(), new Path("input/words.txt"))
            .build();
        env.fromSource(fileSource, WatermarkStrategy.noWatermarks(), "file-source").print();
        env.execute();
    }
}