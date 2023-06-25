# maven脚本

## maven相关网站

- [maven官方网站](https://maven.apache.org/)
- [阿里云maven镜像指南](https://developer.aliyun.com/mvn/guide)

## 配置说明

- 将[java环境配置模板文件](./java-home-template.ps1)复制一份，修改名称为`java-home.ps1`，然后更改里面的jdk路径为真实路径
- 将[maven环境配置模板文件](./maven-home-template.ps1)复制一份，修改名称为`maven-home.ps1`，然后更改里面的maven路径为真实路径
- 在vscode的终端（或者powershell中）中执行`test-jdk.ps1`脚本测试配置是否正确（可以看到java版本信息）
- 在vscode的终端（或者powershell中）中执行`test-maven.ps1`脚本测试配置是否正确（可以看到maven版本信息）
- 在vscode的终端（或者powershell中）中执行`java-cmd.ps1`脚本可以得到一个配置过java环境变量的命令行，可以执行java相关指令
- 在vscode的终端（或者powershell中）中执行`maven-cmd.ps1`脚本可以得到一个配置过maven环境变量的命令行，可以执行maven相关指令
- [使用阿里云maven仓库的配置模板](./settings.xml)
