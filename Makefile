TASKS=build

PLATFORM := $(shell uname || echo Windows)

.PHONY: $(TASKS)

all: tasks;

tasks:
	@echo "------------------------------------------------------------------------"
	@echo "--------                       Tasks                            --------"
	@echo "------------------------------------------------------------------------"
	@echo "How to use:"

	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-19s\033[0m %s\n", "   make " $$1, $$2}'

	@echo "------------------------------------------------------------------------"

# MAIN TARGETS

build: ## Install dependencies and build artifact
	@echo "------------------------------------------------------------------------"
	@echo "---                      Building artifact                        ------"
	@echo "------------------------------------------------------------------------"
ifeq ($(PLATFORM),Windows)
	$(eval COMMAND := mvnw.cmd)
else
	$(eval COMMAND := ./mvnw)
endif

	$(COMMAND) clean install

boot: ## Start the application
	@echo "------------------------------------------------------------------------"
	@echo "--------              Starting the application                  --------"
	@echo "------------------------------------------------------------------------"
ifeq ($(PLATFORM),Windows)
	$(eval COMMAND := mvnw.cmd)
else
	$(eval COMMAND := ./mvnw)
endif

	$(COMMAND) spring-boot:run