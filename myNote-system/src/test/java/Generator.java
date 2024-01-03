/**
 * @author zhishubin
 * @date 2023/12/19 9:04
 * @description
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Generator {

    public static void main(String[] args) {
        /*
            特别注意：生成的时间类型均为：“LocalDateTime”格式，需要假如以下注解方可正常使用
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @JsonDeserialize(using = LocalDateTimeDeserializer.class)
            @JsonSerialize(using = LocalDateTimeSerializer.class)
        */

        //数据库连接
        String url = "jdbc:mysql://192.168.11.135:3306/media";//数据库url
        String username = "root";//账号
        String password = "111111";//密码
        //全局配置参数
        String author = "zhishubin";//作者
        String outputDir = "E:\\workspace\\java\\MyNote\\myNote-third-party\\src\\main\\java";//指定输出目录
        //包配置参数
        String parent = "com.mynote.thirdparty";//父包名
        String moduleName = "minio";//父包模块名
        String entity = "entity";//Entity 实体类包名
        String mapper = "mapper";//Mapper 包名
        String mapperXml = "mapper.xml";//Mapper XML 包名
        String service = "service";//Service 包名
        String serviceImpl = "service.impl";//Service Impl 包名

        //开始生成
        FastAutoGenerator.create(url,username,password)
                //全局配置
                .globalConfig(builder -> {
                    builder.author(author)
                            .outputDir(outputDir)
                            .enableSwagger()
                            ///*.enableSwagger()//开启swagger*//**/
                            .disableOpenDir()
                            .commentDate("yyyy-MM-dd HH:mm:ss");//注释日期
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent(parent)
                            .moduleName(moduleName)
                            .entity(entity)
                            .mapper(mapper)
                            .xml(mapperXml)
                            .service(service)
                            .serviceImpl(serviceImpl)
                    /*.controller(controller)*/;
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude()
                            .addTablePrefix("")
                            //开启生成实体类
                            .entityBuilder()
                            .enableLombok()//开启 lombok 模型
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                            //开启生成mapper
                            .mapperBuilder()
                            .enableBaseResultMap()//启用 BaseResultMap 生成
                            .superClass(BaseMapper.class)//设置父类
                            .enableMapperAnnotation()//开启 @Mapper 注解
                            .formatMapperFileName("%sMapper")//格式化 mapper 文件名称
                            .formatXmlFileName("%sMapper")//格式化 xml 实现类文件名称
                            //开启生成service及impl
                            .controllerBuilder()
                            .enableRestStyle()  // 开启 Restful 风格
                            .formatFileName("%sController")  // 格式化文件名称
                            .serviceBuilder()
                            .formatServiceFileName("%sService")//格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl");//格式化 service 实现类文件名称
                            /*//开启生成controller
                            .controllerBuilder()
                            // 映射路径使用连字符格式，而不是驼峰
                            .enableHyphenStyle()
                            .formatFileName("%sController")//格式化文件名称
                            .enableRestStyle();*/
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                //.templateConfig(builder -> builder.controller(""))//关闭生成controller
                .execute();
    }
}


