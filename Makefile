._DEFAULT_GOAL := run-db

run-db:
	docker run --rm --name json_db_test \
		-e POSTGRES_USER=root \
		-e POSTGRES_PASSWORD=root \
		-e POSTGRES_DB=root \
		-d -p 5432:5432 postgres:9-alpine