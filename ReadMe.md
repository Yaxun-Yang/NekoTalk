# NekoTalk
铲屎官的社交平台
前端 uni-app 后端spring-boot +阿里云ESC实例

## 联合开发者请注意
- 请自行建立以自己名字首字母缩写命名的分支， 例如我的分支叫“yyx”
- 请不要直接进行“git push”操作，将自己整个仓库push到远程，这会加大代码管理的难度，也就是说push操作一定要是针对特定的分支的
- 请在用开发软件打开项目的时候，不要把项目根目录作为开发根目录，这会使项目结构变得难看，例如：当你使用IDEA开发项目时，根目录是spring boot
- 请不要改动master分支， 这个分支用于仓库持有者进行稳定项目的发布

如果你还不太会通过github进行团队协作开发，请根据以下教程来进行操作：

1.  clone远程代码到本地
```
git clone git@github.com:Yaxun-Yang/NekoTalk.git
```
2. 将本地仓库与远程仓库关联()
```
cd NekoTalk
git remote add origin git@github.com:Yaxun-Yang/NekoTalk.git
```
ps: 如果出现`fatal : romote origin already exists.`的提示，说明在上一步clone的时候已经自动添加了远程仓库，可直接进入下一步

3. 从已有的master的基础建立本地的分支（以自己名字缩写命名）
```
 git pull
 git checkout develop
 git checkout -b yyx develop #创建远程分支并进入
```
4. 接下来，你就可以在自己的分支里编写代码了

5. 当你已经写完了代码**并完成了add和commit之后**，你可以选择自己将自己的分支merge到develop分支上（步骤6）或者直接提交自己的分支，并告知仓库所有者，由仓库所有者来进行merge(步骤7)再或者通过pull request的方式来提交代码(请自行学习方法).

6. 你选择自己将修改merge到develop分支上并提交，此时由于远程的develop分支发生了改变，你 的本地分支也发生了改变，你可能需要解决冲突，在这一步中你需要用到IDEA， 它是很棒的可视化merge tool（需要注意的是， 本项目中git 的源目录是NekoTalk而IDEA的源目录是spring boot, 所以当你用IDEA打开项目时请从spring boot这个目录打开，以免创造没有必要的附加文件）

你先需要回到develop分支把它更新为最新
```
git checkout develop
git pull origin develop #检擦本地的develop是否为最新版并更新
```

然后合并通过IDEA把yyx分支合并到develop上，具体的方法如下：
- 保证你的项目此时在develop分支状态下
- 打开IDEA VCS->Git->Merge Changes……
- 选择你需要合并到develop上的分支（此处为yyx）,勾选
ps: 请注意区分有remotes前缀的yyx和无前缀的yyx,此情景应选择无前缀的yyx
- 点击merge
-选择需要解决冲突的文件，双击，进入解决冲突的面板，此面板中，中间为两个分支最近一个公共祖先节点，左右两边分别用红色标注两者冲突的部分，用绿色标注两者共同的部分，你可以很方便的通过点击你想要的代码快来解决冲突
- 将中间的代码修改为你想要的样子后点击确定，commit提交

此时你的develop分支就变成了你想要的样子了,你可以把它提交到github上
```
git push origin develop
```
接下来你还需要删除原先的yyx分支，并在现在的develop上新建它，继续你的下一步开发
```
git branch -d yyx
git checkout -b yyx develop
```

7. 如果你不会merge分支， 你也可以选择直接提交yyx 分支到远程仓库上，然后通过issue或者直接告知仓库所有者，在第5步之后，你执行下面的步骤
- 将本地分支push到远程
```
git push origin yyx
```
- 通知仓库所有者合并分支， 并等待分支合并完成，在此期间，你已经可以把原先的yyx分支删掉了
```
git checkout develop
git branch -d yyx
```
- 在收到合并已完成的反馈之后，你就可以pull最新的develop 并进行进一步的开发
```
git pull origin develop
git checkout -b yyx develop
```