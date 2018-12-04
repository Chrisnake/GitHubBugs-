# GitHubBugs
Java code that reads in text files extracted from git log command lines and finds out how many of the Java files in the repository have been defective or non defective. 

## How it works
1) Find a github repository that you want to analyse for bugs.
2) Use git clone to clone the repository of your choice. 
3) Once the repository is on your computer, use cd to change it to current directory.
4) Use git log stat to find keyword commits that have fixed a problem. (Examples in uploaded Git Log - Stat files)
5) Note : The code works best on repositories with professional bug fixing standards, such as when the developers close a bug with keyword "Closes" or "Fixed".
6) Use git log stat to find the files without commmits that have fixed a problem.
7) Use text files and attach to my Java code, press run and console displays output. 

## Console Output

![image](https://user-images.githubusercontent.com/32743122/49457403-b1a74880-f7e2-11e8-87ee-a004675f70fe.png)
![image](https://user-images.githubusercontent.com/32743122/49457410-b7049300-f7e2-11e8-8c8c-a0841810cc9e.png)
