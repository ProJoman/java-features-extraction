# java-features-extraction
this code is for extracting 11 URLs features completely By Java, it takes a dataset file consisting of URLs and Labels ( 0 for benign and 1 for malicious), and the output is 3 files, output.csv have the result features and labels, outputUrl.csv have the URLs, result features and labels, and last file is exception file, it has the URLs that causes exceptions.

the dataset used URL dataset (ISCX-URL-2016) after we cleaned data and merged multiclass (defacement, phishing,  malware, spam) to one class (malicious), the final dataset consisting of 78910 URLs and their labels.

['urlLen'		'pathLength'	'Querylength' 'URL_Letter_Count'	'URL_DigitCount'	'host_letter_count' 'host_DigitCount'	'SymbolCount_URL'	'spcharUrl'	 'Query_DigitCount'	'pathurlRatio']
