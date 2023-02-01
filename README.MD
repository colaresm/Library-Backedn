# _Deploy on Docker instructions_

## - Steps used to deploy application with Dockerfile only

---
### Command used to build image
```bash
docker build -t <image-name> .
```

### Command used to run container
```bash
docker run -d -p 8066:8080 --name <container-name> --env DATASOURCE_JDBC=<url> --env DATASOURCE_USERNAME=<username> --env DATASOURCE_PASSWORD=<password> <image-name>
```

## - Command used to remove container:

```bash
docker container rm -f <container-name>
```

# _Contributing_

---

If you want, you can contribute in
[issue tracker](https://github.com/wda-trainee/startuplog-frontend/issues/new/choose).


