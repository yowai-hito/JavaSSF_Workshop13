package sg.edu.nus.workshop13.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.JedisClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    private Logger logger = Logger.getLogger(RedisConfig.class.getName());

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private String redisPort;

    @Bean
    @Scope("singleton")
    public RedisTemplate<String, Object> redisTemplate(){
        final RedisStandaloneConfiguration config
        = new RedisStandaloneConfiguration();

        logger.log(Level.INFO, "redisHost > " + redisHost +'\n'
        + "redisPort > " + redisPort);

        config.setHostName(redisHost);
        config.setPort(redisPort.get());

        final JedisClientConfiguration jedisClient = JedisClientConfiguration
            .builder()
            .build()
        final JedisConnectionFactory jedisFac = 
            new JedisConnectionFactory(config,jedisClient);
        jedisFac.afterPropertiesSet();

        RedisTemplate<String, Object> template =
        new RedisTemplate<String,Object>();

        template.setConnectionFactory(jedisFac);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        RedisSerializer<Object> serializer
        = new JdkSerializationRedisSerializer(getClass().getClassLoader());
        template.setValueSerializer(serializer);
        return template;




    }
}
