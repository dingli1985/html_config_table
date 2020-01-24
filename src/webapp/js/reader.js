/**
 * 获取
 * 
 * @param {Object}
 *            chapterId
 */
function getIndex(chapterId) {
	// alert(chapterId);
	var index = chapterId.substring(4, chapterId.indexOf("_chapter"));
	return index;
}
/**
 * 
 * @param {Object}
 *            index
 * @param {Object}
 *            direction 特殊处理 上一页和下一页区别
 */
function setPageIndex(index, direction) {
	console.log("setPageIndex index===========" + index);

	// 选择了前言
	if (index != "_preface") {
		$('#lastPage').attr("disabled", false);
		console.log("index==1 ===========>" + (index == 1));
		if (index == 1) {
			if (direction == "last") {
				$("#lastPage").attr("chapterId", "book_preface_chapter");
				$("#nextPage").attr("chapterId", "book1_chapter");
			} else {
				$("#lastPage").attr("chapterId", "book_preface_chapter");
				$("#nextPage").attr("chapterId", "book2_chapter");
			}

		} else {
			$("#lastPage").attr("chapterId", "book" + (index - 1) + "_chapter");
			$("#nextPage").attr("chapterId",
					"book" + (parseInt(index) + 1) + "_chapter");
		}

	} else {
		$('#lastPage').attr("disabled", true);
		$('#nextPage').attr("disabled", false);
		$("#nextPage").attr("chapterId", "book1_chapter");
	}
	console.log("chapterId lastPage==========="
			+ $("#lastPage").attr("chapterId"));
	console.log("chapterId nextPage==========="
			+ $("#nextPage").attr("chapterId"));
}

/**
 * 选择对应的章节显示
 * 
 * @param {Object}
 *            chapter
 */
function showChapter(chapter) {
	var selectChapterId = chapter.id + "_chapter";
	// console.log($("#" + chapter.id + "_chapter").text());
	// console.log($("#" + chapter.id + "_chapter").css("display"));
	$("#" + selectChapterId).css("display", "block");
	console.log("chapterId===========" + $("#lastPage").attr("chapterId"));
	console.log("disabled===========" + $("#lastPage").attr("disabled"));
	console.log("selectedChapterId========" + $("#selectedChapterId").val());
	var ori_selected_chapter_id = $("#selectedChapterId").val();
	// 原来章节的DIV变为不显示
	$("#" + ori_selected_chapter_id).css("display", "none");
	// 当前点击的章节DIV显示
	$("#" + chapter.id + "_chapter").css("display", "block");
	$("#selectedChapterId").val(selectChapterId);
	// 获取章节索引
	var index = getIndex(selectChapterId);
	var maxChapterIndex = $("#maxChapterIndex").val();
	console.log("index========" + index);
	console.log("maxChapterIndex========" + maxChapterIndex);
	setPageIndex(index);
	// 选择了最大的章节
	if (parseInt(maxChapterIndex) == index) {
		$('#nextPage').attr("disabled", true);
	} else {
		$('#nextPage').attr("disabled", false);
	}
	console.log("maxChapterIndex===========" + $("#maxChapterIndex").val());
}

function setCatalog(numType, index, secPrefix, type) {
	// console.log(numType);
	if (numType == "0") {
		$("#catalog")
				.append(
						"<li><a  id='book"
								+ (index - 1)
								+ "' href='javascript:void(0);' onclick='showChapter(this);'>"
								+ secPrefix + (index - 1) + type + "</a></li>");
	} else {
		$("#catalog")
				.append(
						"<li><a  id='book"
								+ (index - 1)
								+ "' href='javascript:void(0);' onclick='showChapter(this);'>"
								+ secPrefix
								+ tools
										.Arabia_To_SimplifiedChinese((index - 1))
								+ type + "</a></li>");
	}
}

function secChapter(text) {
	var secPrefix = "第";
	var secVariable = "1";
	var pos = text.indexOf(secPrefix + secVariable);
	var chapterType = "0";
	if (pos == -1) {
		pos = text.indexOf(secPrefix + "一");
		chapterType = "1";
	}
	// console.log(pos);
	// 获取之后的第一个汉字
	var type = text.substr(pos + (secPrefix + secVariable).length, 1);
	// console.log(type);
	var qianyan = text.substr(0, pos);
	var mainBody = text.substring(pos);
	console.log(qianyan)

	var chapterPos = 0;
	var index = 1;
	var chapterText = "";
	// console.log(chapter_c);
	$("#catalog").append("<ul>");
	$("#catalog")
			.append(
					"<li><a  id='book_preface' href='javascript:void(0);' onclick='showChapter(this);'>前言／序</a></li>");
	$("#content").append(
			"<div class='chaper_div' id='book_preface_chapter'><pre>" + qianyan
					+ "</pre></div>");
	// 初始化，默认选择
	$("#selectedChapterId").val("book_preface_chapter");
	$("#book_preface_chapter").css("display", "block");
	// 初始化 下一页只想第一章／回／节
	$("nextPage").attr("chapterId", "book1_chapter");
	while (chapterPos != -1) {
		index++;
		console.log("read--------------->" + index);
		chapterPos = mainBody.indexOf(secPrefix + index + type);
		// 如果是中文的数字
		if (chapterType == "1") {
			chapterPos = mainBody.indexOf(secPrefix
					+ tools.Arabia_To_SimplifiedChinese(index) + type);

		}
		if (chapterPos != -1) {
			setCatalog(chapterType, index, secPrefix, type);
			chapterText = mainBody.substr(0, chapterPos);
			$("#content").append(
					"<div class='chaper_div' id='book" + (index - 1)
							+ "_chapter'><pre>" + chapterText + "</pre></div>");

		}
		mainBody = mainBody.substring(chapterPos);
	}
	// console.log("mainBody====>"+mainBody);
	// 最后一章节
	setCatalog(chapterType, index, secPrefix, type);
	// 最大章节数
	$("#maxChapterIndex").val((index - 1));
	$("#content").append(
			"<div id='book" + (index - 1)
					+ "_chapter' class='chaper_div'><pre>" + mainBody
					+ "</pre></div>");
	$("#catalog").append("</ul>");

}

$(function() {
	console.log("lastPage=========" + $("#lastPage").attr("chapterId"));
	console.log("nextPage=========" + $("#nextPage").attr("chapterId"));
	console.log("lastPage=========" + $("#lastPage").attr("disabled"));

	$("#import").click(function() { // 点击导入按钮，使files触发点击事件，然后完成读取文件的操作。
		// 创建实例
		$("#files").click();
	});
	$("#lastPage").click(
			function() {
				console.log("id===========" + $(this).attr("chapterId"));
				var currentChapterId = $(this).attr("chapterId");
				var index = getIndex(currentChapterId);
				setPageIndex(index);
				console.log("lastPage index===========" + index);
				console.log("display none===========" + "#book"
						+ (parseInt(index) + 1) + "_chapter");
				$("#nextPage").attr("disabled", false);
				if (index != "_preface") {
					$("#book" + (parseInt(index) + 1) + "_chapter").css(
							"display", "none");
					$("#book" + index + "_chapter").css("display", "block");
					$("#selectedChapterId").val("book" + index + "_chapter");
				} else { // 当上一页指向的是前言时
					$("#selectedChapterId").val("book_preface_chapter");
					$("#book_preface_chapter").css("display", "block");
					$("#book1_chapter").css("display", "none");
				}

			});
	$("#nextPage").click(
			function() {
				console.log("id===========" + $(this).attr("chapterId"));
				var currentChapterId = $(this).attr("chapterId");
				var index = getIndex(currentChapterId);
				var maxChapterIndex = $("#maxChapterIndex").val();
				setPageIndex(index);
				console.log("nextPage index===========" + index);
				console.log("nextPage index===========" + index);
				console.log("nextPage maxChapterIndex==========="
						+ maxChapterIndex);
				console.log("nextPage display none===========" + "#book"
						+ (parseInt(index) + 1) + "_chapter");

				if (index != 1) {
					console.log("nextPage if===========11111111111");
					$("#book" + (parseInt(index) - 1) + "_chapter").css(
							"display", "none");
					$("#book" + index + "_chapter").css("display", "block");
					$("#selectedChapterId").val("book" + index + "_chapter");
					if (parseInt(index) == parseInt(maxChapterIndex)) {
						$(this).attr("disabled", true);
					}
				} else {
					console.log("nextPage else===========2222222");

					$("#book_preface_chapter").css("display", "none");
					$("#book" + index + "_chapter").css("display", "block");
					$("#selectedChapterId").val("book" + index + "_chapter");

				}

			});

	$("#files").on("change", function() {
		$("#catalog").empty();
		$("#content").empty();
		var selectedFile = document.getElementById("files").files[0]; // 获取读取的File对象
		var encode = document.getElementById("encode").value;
		var name = selectedFile.name; // 读取选中文件的文件名
		var size = selectedFile.size; // 读取选中文件的大小
		// alert(selectedFile.encoding);
		console.log("文件名:" + name + "大小：" + size);
		// alert(name);
		var reader = new FileReader(); // 这里是核心！！！读取操作就是由它完成的。
		reader.readAsText(selectedFile, encode); // 读取文件的内容

		reader.onload = function() {
			// console.log("Whole================="+this.result);//当读取完成之后会回调这个函数，然后此时文件的内容存储到了result中。直接操作即可。
			secChapter(this.result);
			// $("#content").append("<div>"+this.result+"</div>");

		}

	});
});