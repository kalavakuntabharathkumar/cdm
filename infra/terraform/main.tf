terraform {
  required_version = ">= 1.5.0"
  required_providers { aws = { source = "hashicorp/aws", version = "~> 5.0" } }
}
provider "aws" { region = var.aws_region }
variable "aws_region" { type = string, default = "ap-south-1" }
variable "project_name" { type = string, default = "credit-decision-demo" }
output "project_name" { value = var.project_name }
