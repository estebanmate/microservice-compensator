KUBERNETES_SERVICE_NAME=compensator-api
# starts netty on localhost:8080

.PHONY: run
run: build
	./gradlew run

# runs unit tests
.PHONY: test
test: build
	MICRONAUT_PROFILES_ACTIVE=test ./gradlew clean test

.PHONY: clean
clean:
	./gradlew clean 
	rm -rf deployment

# bundles app for production
.PHONY: build
build:  
	./gradlew build

.PHONY: install
install:
	./gradlew build

# builds the production docker image
.PHONY: build-docker-image
build-docker-image:
	mkdir target
	cp build/libs/*all.jar target/
	docker build -t ${KUBERNETES_SERVICE_NAME} ${ARGS} .

# starts the development docker image on localhost:4200
.PHONY: run-docker-image
run-docker-image:
	docker run -p 8080:8080 ${KUBERNETES_SERVICE_NAME}

# starts the production docker image on localhost:8080
.PHONY: run-prod-image
run-prod-image:
	docker run -p 8080:8080 ${KUBERNETES_SERVICE_NAME}

.PHONY: continuous-deployment-scripts
continuous-deployment-scripts: deployment

deployment: 
# SSH Permissions not working on CircleCI v2.0
# https://discuss.circleci.com/t/ssh-permissions-not-working-on-circleci-v2-0/15962
ifeq ($(CIRCLECI),true)
	ssh-keygen -y -f ~/.ssh/id_rsa > ~/.ssh/id_rsa.pub
	ssh-add -d ~/.ssh/id_rsa.pub
endif
	git clone --depth 2 git@github.com:yoigo-thor/continuous-deployment-scripts.git cd-scripts
	mv cd-scripts/deployment deployment
	rm -rf cd-scripts


