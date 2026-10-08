# AWS deployment starter
This folder documents the intended AWS shape. The local demo uses Docker Compose; before deploying, create a VPC, private subnets, ECS cluster, ECR repositories, IAM task/execution roles, secrets, and managed Kafka/MySQL equivalents. Never commit credentials.

The minimal provider declaration is included as a starting point. Add reviewed modules and environment-specific variables before `terraform apply`.
