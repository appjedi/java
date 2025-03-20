# connect to AWS Node instance
echo $1
ssh -i /Users/roberttimlin/Documents/aws/AWSEc2Spring.pem ec2-user@$1
