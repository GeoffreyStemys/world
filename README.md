## World

App for debugging

Install `sdkman` with `curl -s "https://get.sdkman.io" | bash`, then to autoload the `.sdkmanrc`
set `sdkman_auto_env=true` in `$HOME/.sdkman/etc/config`

```shell
./gradlew clean build -xtest
```

Start spring app:

```shell
./gradlew clean bootRun
```

```shell
./gradlew jibBuildLocal -Psemver.stage=final -Psemver.scope=minor
```

```shell
curl http://localhost:8080/world/
```

### Some useful gradle plugins tasks

- Build local docker images: `./gradlew jibBuildLocal -xtest -Psemver.stage=final -Psemver.scope=minor`
- Build images and push in aws ecr: `./gradlew jibPushAwsEcr -xtest -Psemver.stage=snapshot`
- Publish snapshot jars in nexus: `./gradlew publish -xtest`
- Stop all hidden gradle daemons: `./gradlew --stop`

### Docker compose

Stop all docker containers if they remained live by mistake:

```shell 
docker stop $(docker ps -a -q) && docker rm $(docker ps -a -q)
```

Delete all gradle caches:
```shell
rm -rf .gradle /gradle/plugins/.gradle
cd ${HOME}/.gradle && rm -rf caches kotlin-profile notifications wrapper daemon jdks native workers
```

webmvc otel metrics names:
```
http_server_request_duration_seconds_bucket
http_server_request_duration_seconds_count
http_server_request_duration_seconds_sum
otlp_exporter_exported_total
otlp_exporter_seen_total

target_info
```

https://betterstack.com/community/guides/observability/opentelemetry-collector/
http://localhost:55679/debug/tracez
https://www.otelbin.io/

```shell
TAG="1.0.0"
git tag $TAG
git push --tags
```