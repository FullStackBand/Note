<div id="topics">
	<div class="post">
		<h1 class="postTitle">
			<a id="cb_post_title_url" class="postTitle2" href="https://www.cnblogs.com/qingchunshiguang/p/8011103.html">前端布局神器display:flex</a>
		</h1>
		<div class="clear"></div>
		<div class="postBody">
			<div id="cnblogs_post_body" class="blogpost-body"><h1 class="title"><img style="font-size: 14px" src="//upload-images.jianshu.io/upload_images/1679823-dea0e956685154e5.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-dea0e956685154e5.png" data-original-width="700" data-original-height="368" data-original-format="image/png" data-original-filesize="13065"></h1>
<div class="show-content" data-note-content="">
<p>2009年，W3C提出了一种新的方案--Flex布局，可以简便、完整、响应式地实现各种页面布局。目前已得到所有现在浏览器的支持。</p>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="899" data-height="317"><img src="//upload-images.jianshu.io/upload_images/1679823-909f698a8d82fbc4.jpg" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-909f698a8d82fbc4.jpg" data-original-width="899" data-original-height="317" data-original-format="image/jpeg" data-original-filesize="17225"></div>
</div>
<div class="image-caption">flex浏览器支持</div>
</div>
<h2>一、Flex布局是什么？</h2>
<p>Flex是Flexible Box的缩写，翻译成中文就是“弹性盒子”，用来为盒装模型提供最大的灵活性。任何一个容器都可以指定为Flex布局。</p>
<pre class="hljs undefined"><code class="hljs scss"><span class="hljs-selector-class">.box</span>{
    <span class="hljs-attribute">display</span>: -webkit-flex; <span class="hljs-comment">/*在webkit内核的浏览器上使用要加前缀*/</span>
    <span class="hljs-attribute">display</span>: flex; <span class="hljs-comment">//将对象作为弹性伸缩盒显示</span>
}
</code></pre>
<p>当然，行内元素也可以使用Flex布局。</p>
<pre class="hljs undefined"><code class="hljs scss"><span class="hljs-selector-class">.box</span> {
    <span class="hljs-attribute">display</span>: inline-flex; <span class="hljs-comment">//将对象作为内联块级弹性伸缩盒显示</span>
}<br><br>兼容性写法<br></code></pre>
<pre class="hljs undefined"><code class="hljs css"><span class="hljs-selector-class">.box</span> {
    <span class="hljs-attribute">display</span>: flex || inline-flex;
}</code></pre>
<h2>二、基本概念</h2>
<p>采用Flex布局的元素，被称为Flex容器(flex container)，简称“容器”。其所有子元素自动成为容器成员，成为Flex项目(Flex item)，简称“项目”。</p>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="563" data-height="333"><img src="//upload-images.jianshu.io/upload_images/1679823-6ea441649bdf542a.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-6ea441649bdf542a.png" data-original-width="563" data-original-height="333" data-original-format="image/png" data-original-filesize="10005"></div>
</div>
<div class="image-caption">结构示意图</div>
</div>
<br>容器默认存在两根主轴：水平方向主轴(main axis)和垂直方向交叉轴(cross axis)，默认项目按主轴排列。
<ul>
<li>main start/main end：主轴开始位置/结束位置；</li>
<li>cross start/cross end：交叉轴开始位置/结束位置；</li>
<li>main size/cross size：单个项目占据主轴/交叉轴的空间；</li>






</ul>
<h2>三、容器属性</h2>
<blockquote>
<p>设置在容器上的属性有6种。</p>






</blockquote>
<ul>
<li>flex-direction</li>
<li>flex-wrap</li>
<li>flex-flow</li>
<li>justify-content</li>
<li>align-item</li>
<li>align-content</li>






</ul>
<h4>flex-direction属性：决定主轴的方向（即项目的排列方向）</h4>
<pre class="hljs undefined"><code class="hljs css"><span class="hljs-selector-class">.box</span> {
   <span class="hljs-attribute">flex-direction</span>: row | row-reverse | column | column-reverse;
}
</code></pre>
<ul>
<li>row（默认）：主轴水平方向，起点在左端；</li>
<li>row-reverse：主轴水平方向，起点在右端；</li>
<li>column：主轴垂直方向，起点在上边沿；</li>
<li>column-reserve：主轴垂直方向，起点在下边沿。</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="796" data-height="203"><img src="//upload-images.jianshu.io/upload_images/1679823-a91b68309c12e105.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-a91b68309c12e105.png" data-original-width="796" data-original-height="203" data-original-format="image/png" data-original-filesize="7519"></div>
</div>
<div class="image-caption">主轴的4个方向</div>
</div>
<h4>flex-wrap属性：定义换行情况</h4>
<blockquote>
<p>默认情况下，项目都排列在一条轴线上，但有可能一条轴线排不下。</p>
</blockquote>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="798" data-height="276"><img src="//upload-images.jianshu.io/upload_images/1679823-68d21ca039ba28db.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-68d21ca039ba28db.png" data-original-width="798" data-original-height="276" data-original-format="image/png" data-original-filesize="2356"></div>
</div>
<div class="image-caption">一条轴线排不下</div>
</div>
<pre class="hljs undefined"><code class="hljs css">
<span class="hljs-selector-class">.box</span>{
   <span class="hljs-attribute">flex-wrap</span>: nowrap | wrap | wrap-reverse;
}
</code></pre>
<ul>
<li>
<p>nowrap（默认）：不换行；</p>
<br>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="1222" data-height="427"><img src="//upload-images.jianshu.io/upload_images/1679823-c51dd7b251cdddec.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-c51dd7b251cdddec.png" data-original-width="1222" data-original-height="427" data-original-format="image/png" data-original-filesize="9064"></div>






</div>
<div class="image-caption">不换行nowrap</div>






</div>






</li>
<li>
<p>wrap：换行，第一行在上方；</p>






<br>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="1092" data-height="426"><img src="//upload-images.jianshu.io/upload_images/1679823-fd48f147e6dc7ac6.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-fd48f147e6dc7ac6.png" data-original-width="1092" data-original-height="426" data-original-format="image/png" data-original-filesize="8563"></div>






</div>
<div class="image-caption">换行，第一行在上</div>






</div>






</li>
<li>
<p>wrap-reverse：换行，第一行在下方。</p>






<br>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="1089" data-height="414"><img src="//upload-images.jianshu.io/upload_images/1679823-77847157380e23e4.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-77847157380e23e4.png" data-original-width="1089" data-original-height="414" data-original-format="image/png" data-original-filesize="8281"></div>






</div>
<div class="image-caption">换行，第一行在下</div>






</div>






</li>






</ul>
<h4>flex-flow属性：flex-direction和flex-wrap的简写，默认row nowrap</h4>
<pre class="hljs undefined"><code class="hljs css"><span class="hljs-selector-class">.box</span>{
    <span class="hljs-attribute">flex-flow</span>: &lt;flex-direction&gt; || &lt;flex-wrap&gt;;
}
</code></pre>
<h4>justify-content属性：定义项目在主轴上的对齐方式。</h4>
<blockquote>
<p>对齐方式与轴的方向有关，本文中假设主轴从左到右。</p>
</blockquote>
<pre class="hljs undefined"><code class="hljs ruby">.box {
   justify-<span class="hljs-symbol">content:</span> start <span class="hljs-params">| <span class="hljs-keyword">end</span> |</span> flex-start <span class="hljs-params">| flex-<span class="hljs-keyword">end</span> |</span> center <span class="hljs-params">| left |</span> right <span class="hljs-params">| space-between |</span> space-around <span class="hljs-params">| space-evenly |</span> stretch <span class="hljs-params">| safe |</span> unsafe <span class="hljs-params">| baseline |</span> first baseline <span class="hljs-params">| last baseline;
}
</span></code></pre>
<ul>
<li>
<p>flex-start（默认值）：左对齐；</p>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1090" data-height="207"><img src="//upload-images.jianshu.io/upload_images/1679823-c6c33e14817aaeb7.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-c6c33e14817aaeb7.png" data-original-width="1090" data-original-height="207" data-original-format="image/png" data-original-filesize="4072"></div>
</div>
<div class="image-caption">左对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
</li>
<li>
<p>flex-end：右对齐；</p>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1088" data-height="206"><img src="//upload-images.jianshu.io/upload_images/1679823-958fc54a2805ae83.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-958fc54a2805ae83.png" data-original-width="1088" data-original-height="206" data-original-format="image/png" data-original-filesize="4100"></div>
</div>
<div class="image-caption">右对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
</li>
<li>
<p>center：居中；</p>
</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1091" data-height="209"><img src="//upload-images.jianshu.io/upload_images/1679823-0e4934ebf5828c81.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-0e4934ebf5828c81.png" data-original-width="1091" data-original-height="209" data-original-format="image/png" data-original-filesize="4063"></div>
</div>
<div class="image-caption">居中对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>space-between：两端对齐，项目之间间隔相等；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1091" data-height="209"><img src="//upload-images.jianshu.io/upload_images/1679823-e3dda677d9efc9dd.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-e3dda677d9efc9dd.png" data-original-width="1091" data-original-height="209" data-original-format="image/png" data-original-filesize="4177"></div>
</div>
<div class="image-caption">两端对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>space-around：每个项目两侧的间隔相等，即项目之间的间隔比项目与边框的间隔大一倍。</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1090" data-height="212"><img src="//upload-images.jianshu.io/upload_images/1679823-4e4c94cfab42cebd.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-4e4c94cfab42cebd.png" data-original-width="1090" data-original-height="212" data-original-format="image/png" data-original-filesize="4235"></div>
</div>
<div class="image-caption">两侧间隔相等</div>
</div>
<h4>align-items属性：定义在交叉轴上的对齐方式</h4>
<blockquote>
<p>对齐方式与交叉轴的方向有关，假设交叉轴从下到上。</p>
</blockquote>
<pre class="hljs undefined"><code class="hljs css"><span class="hljs-selector-class">.box</span>{
    <span class="hljs-attribute">align-items</span>: flex-start | flex-end | center | baseline | stretch;
}
</code></pre>
<ul>
<li>flex-start：起点对齐；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1089" data-height="416"><img src="//upload-images.jianshu.io/upload_images/1679823-794781b09ba1222b.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-794781b09ba1222b.png" data-original-width="1089" data-original-height="416" data-original-format="image/png" data-original-filesize="8091"></div>
</div>
<div class="image-caption">起点对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>flex-end：终点对齐；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1090" data-height="411"><img src="//upload-images.jianshu.io/upload_images/1679823-eadf0e3c23e5f6ff.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-eadf0e3c23e5f6ff.png" data-original-width="1090" data-original-height="411" data-original-format="image/png" data-original-filesize="7988"></div>
</div>
<div class="image-caption">终点对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>center：中点对齐；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1089" data-height="409"><img src="//upload-images.jianshu.io/upload_images/1679823-70da312a8c49de64.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-70da312a8c49de64.png" data-original-width="1089" data-original-height="409" data-original-format="image/png" data-original-filesize="7774"></div>
</div>
<div class="image-caption">中点对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>baseline：项目的第一行文字的基线对齐；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1093" data-height="412"><img src="//upload-images.jianshu.io/upload_images/1679823-7add48ac84c6d397.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-7add48ac84c6d397.png" data-original-width="1093" data-original-height="412" data-original-format="image/png" data-original-filesize="7927"></div>
</div>
<div class="image-caption">基线对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>stretch（默认值）：如果项目未设置高度或设为auto，将占满整个容器的高度。</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="1093" data-height="411"><img src="//upload-images.jianshu.io/upload_images/1679823-9711dc77e87507b5.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-9711dc77e87507b5.png" data-original-width="1093" data-original-height="411" data-original-format="image/png" data-original-filesize="7769"></div>
</div>
</div>
<h4>align-content属性：定义多根轴线的对齐方式</h4>
<blockquote>
<p>如果项目只有一根轴线，该属性不起作用。<br>所以，容器必须设置flex-wrap：···；</p>





</blockquote>
<pre class="hljs undefined"><code class="hljs css"><span class="hljs-selector-class">.box</span>{
    <span class="hljs-attribute">align-content</span>: flex-start | flex-end | center | space-between | space-around | stretch;
}
</code></pre>
<ul>
<li>flex-start：与交叉轴的起点对齐；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="672" data-height="409"><img src="//upload-images.jianshu.io/upload_images/1679823-3d8d3dd45d5a0dad.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-3d8d3dd45d5a0dad.png" data-original-width="672" data-original-height="409" data-original-format="image/png" data-original-filesize="5815"></div>
</div>
<div class="image-caption">起点对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>flex-end：与交叉轴的终点对齐；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="672" data-height="420"><img src="//upload-images.jianshu.io/upload_images/1679823-5f155d8e95a419fe.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-5f155d8e95a419fe.png" data-original-width="672" data-original-height="420" data-original-format="image/png" data-original-filesize="6043"></div>
</div>
<div class="image-caption">终点对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>center：与交叉轴的中点对齐；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="679" data-height="405"><img src="//upload-images.jianshu.io/upload_images/1679823-b1a3ed27fe64e88d.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-b1a3ed27fe64e88d.png" data-original-width="679" data-original-height="405" data-original-format="image/png" data-original-filesize="5948"></div>
</div>
<div class="image-caption">中点对齐</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>space-between：与交叉轴的两端对齐，轴线之间的间隔平均分布；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="672" data-height="413"><img src="//upload-images.jianshu.io/upload_images/1679823-b013b001bff86782.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-b013b001bff86782.png" data-original-width="672" data-original-height="413" data-original-format="image/png" data-original-filesize="5978"></div>
</div>
<div class="image-caption">轴线之间等间距</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>space-around：每根轴线两侧的间隔相等，即轴线之间的间隔比轴线与边框的间隔大一倍；</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="673" data-height="410"><img src="//upload-images.jianshu.io/upload_images/1679823-3dd9a6ed68b35b72.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-3dd9a6ed68b35b72.png" data-original-width="673" data-original-height="410" data-original-format="image/png" data-original-filesize="5877"></div>
</div>
<div class="image-caption">轴线两侧等间距</div>
<div class="image-caption">&nbsp;</div>
</div>
<ul>
<li>stretch（默认值）：轴线占满整个交叉轴。</li>
</ul>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="670" data-height="410"><img src="//upload-images.jianshu.io/upload_images/1679823-11a08044da41b365.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-11a08044da41b365.png" data-original-width="670" data-original-height="410" data-original-format="image/png" data-original-filesize="5915"></div>
</div>
<div class="image-caption">项目未设置高度时</div>
</div>
<p>有意思的是，当你不给项目设置高度但是给容器设置align-content不为stretch时，同一轴线上的项目的高度将等于项目中高度最高的项目。</p>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="669" data-height="411"><img src="//upload-images.jianshu.io/upload_images/1679823-57180f41a0e740bf.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-57180f41a0e740bf.png" data-original-width="669" data-original-height="411" data-original-format="image/png" data-original-filesize="6395"></div>
</div>
</div>
<h2>四、项目的属性</h2>
<blockquote>
<p>设置在项目上的属性也有6个。</p>
</blockquote>
<ul>
<li>order</li>
<li>flex-grow</li>
<li>flex-shrink</li>
<li>flex-basis</li>
<li>flex</li>
<li>align-self</li>
</ul>
<h4>order属性：定义项目的排列顺序。</h4>
<blockquote>
<p>数值越小，排列越靠前，默认为0，可以是负值。</p>
</blockquote>
<pre class="hljs undefined"><code class="hljs css"><span class="hljs-selector-class">.item</span> {
    <span class="hljs-attribute">order</span>: &lt;整数&gt;;
}</code></pre>
<div class="image-package">
<div class="image-container">
<div class="image-view" data-width="751" data-height="480"><img src="//upload-images.jianshu.io/upload_images/1679823-775519de997dc5be.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-775519de997dc5be.png" data-original-width="751" data-original-height="480" data-original-format="image/png" data-original-filesize="3372"></div>
</div>
<div class="image-caption">展示效果不明显，直接盗图</div>
</div>
<h4>flex-grow属性：定义项目的放大比例</h4>
<blockquote>
<p>默认值为0，即如果空间有剩余，也不放大。<br>可以是小数，按比例占据剩余空间。</p>






</blockquote>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="970" data-height="214"><img src="//upload-images.jianshu.io/upload_images/1679823-7b6f310d3adbfc04.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-7b6f310d3adbfc04.png" data-original-width="970" data-original-height="214" data-original-format="image/png" data-original-filesize="3768"></div>






</div>
<div class="image-caption">默认情况</div>






</div>
<pre class="hljs undefined"><code class="hljs css">
<span class="hljs-selector-class">.item</span>{
    <span class="hljs-attribute">flex-grow</span>: &lt;数字&gt;;
}
</code></pre>
<p><em>若所有项目的flex-grow的数值都相同，则等分剩余空间</em></p>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="970" data-height="210"><img src="//upload-images.jianshu.io/upload_images/1679823-f3bc44487c3b234f.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-f3bc44487c3b234f.png" data-original-width="970" data-original-height="210" data-original-format="image/png" data-original-filesize="3697"></div>
</div>
<div class="image-caption">等分剩余空间</div>
</div>
<p><em>若果有一个项目flex-grow为2，其余都为1，则该项目占据剩余空间是其余的2倍</em></p>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="968" data-height="209"><img src="//upload-images.jianshu.io/upload_images/1679823-7f6a24a222e3ac35.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-7f6a24a222e3ac35.png" data-original-width="968" data-original-height="209" data-original-format="image/png" data-original-filesize="3712"></div>
</div>
<div class="image-caption">不等分占据</div>
</div>
<h4>flex-shrink属性：定义项目的缩小比例</h4>
<blockquote>
<p>默认值都为1，即如果空间不足将等比例缩小。<br>如果有一个项目的值为0，其他项目为1，当空间不足时，该项目不缩小。<br>负值对该属性无效，容器不应该设置flex-wrap。</p>






</blockquote>
<pre class="hljs undefined"><code class="hljs css">
<span class="hljs-selector-class">.item</span>{
    <span class="hljs-attribute">flex-shrink</span>: &lt;非负整数&gt;;
}
</code></pre>
<p>如果一个项目设置flex-shrink为0；而其他项目都为1，则空间不足时，该项目不缩小。</p>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="870" data-height="211"><img src="//upload-images.jianshu.io/upload_images/1679823-4b731d6538802584.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-4b731d6538802584.png" data-original-width="870" data-original-height="211" data-original-format="image/png" data-original-filesize="4500"></div>
</div>
<div class="image-caption">设置flex-shrink为0的项目不缩小</div>
</div>
<p>如果所有项目都为0，则当空间不足时，项目撑破容器而溢出。</p>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="1228" data-height="227"><img src="//upload-images.jianshu.io/upload_images/1679823-e75475e14fd014fb.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-e75475e14fd014fb.png" data-original-width="1228" data-original-height="227" data-original-format="image/png" data-original-filesize="5165"></div>
</div>
<div class="image-caption">不缩小</div>
</div>
<p>如果设置项目的flex-shrink不为0的非负数效果同设置为1。</p>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="1157" data-height="229"><img src="//upload-images.jianshu.io/upload_images/1679823-6ce2b966a4c3dd12.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-6ce2b966a4c3dd12.png" data-original-width="1157" data-original-height="229" data-original-format="image/png" data-original-filesize="5850"></div>
</div>
</div>
<h4>flex-basis属性：定义在分配多余空间之前，项目占据的主轴空间。</h4>
<blockquote>
<p>默认值为auto，浏览器根据此属性检查主轴是否有多余空间。</p>
</blockquote>
<pre class="hljs undefined"><code class="hljs css">
<span class="hljs-selector-class">.item</span>{
    <span class="hljs-attribute">flex-basis</span>:  &lt;auto或者px&gt;;
}
</code></pre>
<p>注意设置的flex-basis是分配多余空间之前项目占据的主轴空间，如果空间不足则默认情况下该项目也会缩小。</p>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="866" data-height="209"><img src="//upload-images.jianshu.io/upload_images/1679823-a4b86e5070adf166.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-a4b86e5070adf166.png" data-original-width="866" data-original-height="209" data-original-format="image/png" data-original-filesize="4387"></div>
</div>
<div class="image-caption">设置flex-basis为350px，但空间充足</div>
</div>
<p>&nbsp;</p>
<div class="image-package">
<div class="image-container">
<div class="image-container-fill">&nbsp;</div>
<div class="image-view" data-width="872" data-height="214"><img src="//upload-images.jianshu.io/upload_images/1679823-a1c9be91ba8d3879.png" alt="" data-original-src="//upload-images.jianshu.io/upload_images/1679823-a1c9be91ba8d3879.png" data-original-width="872" data-original-height="214" data-original-format="image/png" data-original-filesize="4599"></div>
</div>
<div class="image-caption">空间不足，项目缩小，小于设定值</div>
</div>
<h4>flex属性是flex-grow，flex-shrink和flex-basis的简写</h4>
<blockquote>
<p>默认值为0 1 auto，第一个属性必须，后两个属性可选。</p>
</blockquote>
<pre class="hljs undefined"><code class="hljs fsharp">.item{
    flex: none | <span class="hljs-meta">[&lt;flex-grow&gt;&lt;flex-shrink&gt;&lt;flex-basis&gt;]</span>;
}

</code></pre>
<ul>
<li>可以用&nbsp;<code>flex:auto;</code>&nbsp;代替&nbsp;<code>flex: 1 1 auto;</code>；</li>
<li>可以用&nbsp;<code>flex: none;</code>代替&nbsp;<code>flex: 0 0 auto</code>；</li>
</ul>
<pre class="hljs undefined"><code class="hljs shell"><span class="hljs-meta">#</span><span class="bash"><span class="hljs-comment">###align-self属性：允许单个项目与其他项目有不一样的对齐方式</span></span>
<span class="hljs-meta">&gt;</span><span class="bash">默认值为auto，表示继承父元素的align-items属性，并可以覆盖align-items属性。</span>

</code></pre>
<p>.item{<br>align-self: auto | flex-start | flex-end | center | baseline | stretch;<br>}</p>
<p>&nbsp;</p>
<p><span style="font-size: 18px; color: #ff6600">如果你觉得文章对你有所帮助，别忘了打赏哦！<span class="tjSpan">因为有你的支持，才是我续写下篇的动力和源泉！</span></span></p>
<p><img src="https://img2018.cnblogs.com/blog/1050358/201811/1050358-20181110103805407-1172344704.png" alt=""></p>





</div></div><div id="MySignature"></div>
<div class="clear"></div>
<div id="blog_post_info_block">
<div id="BlogPostCategory"></div>
<div id="EntryTag">标签: <a href="https://www.cnblogs.com/qingchunshiguang/tag/Web%E5%89%8D%E7%AB%AF/">Web前端</a></div>
<div id="blog_post_info"><div id="green_channel">
        <a href="javascript:void(0);" id="green_channel_digg" onclick="DiggIt(8011103,cb_blogId,1);green_channel_success(this,'谢谢推荐！');">好文要顶</a>
            <a id="green_channel_follow" onclick="follow('05cc7c23-7e9b-e611-845c-ac853d9f53ac');" href="javascript:void(0);">关注我</a>
    <a id="green_channel_favorite" onclick="AddToWz(cb_entryId);return false;" href="javascript:void(0);">收藏该文</a>
    <a id="green_channel_weibo" href="javascript:void(0);" title="分享至新浪微博" onclick="ShareToTsina()"><img src="//common.cnblogs.com/images/icon_weibo_24.png" alt=""></a>
    <a id="green_channel_wechat" href="javascript:void(0);" title="分享至微信" onclick="shareOnWechat()"><img src="//common.cnblogs.com/images/wechat.png" alt=""></a>
</div>
<div id="author_profile">
    <div id="author_profile_info" class="author_profile_info">
            <a href="http://home.cnblogs.com/u/qingchunshiguang/" target="_blank"><img src="//pic.cnblogs.com/face/1050358/20171112104842.png" class="author_avatar" alt=""></a>
        <div id="author_profile_detail" class="author_profile_info">
            <a href="http://home.cnblogs.com/u/qingchunshiguang/">青春时光</a><br>
            <a href="http://home.cnblogs.com/u/qingchunshiguang/followees">关注 - 1</a><br>
            <a href="http://home.cnblogs.com/u/qingchunshiguang/followers">粉丝 - 14</a>
        </div>
    </div>
    <div class="clear"></div>
    <div id="author_profile_honor"></div>
    <div id="author_profile_follow">
                <a href="javascript:void(0);" onclick="follow('05cc7c23-7e9b-e611-845c-ac853d9f53ac');return false;">+加关注</a>
    </div>
</div>
<div id="div_digg">
    <div class="diggit" onclick="votePost(8011103,'Digg')">
        <span class="diggnum" id="digg_count">8</span>
    </div>
    <div class="buryit" onclick="votePost(8011103,'Bury')">
        <span class="burynum" id="bury_count">0</span>
    </div>
    <div class="clear"></div>
    <div class="diggword" id="digg_tips">
    </div>
</div>
<script type="text/javascript">
    currentDiggType = 0;
</script></div>
<div class="clear"></div>
<div id="post_next_prev"><a href="https://www.cnblogs.com/qingchunshiguang/p/8011049.html" class="p_n_p_prefix">« </a> 上一篇：<a href="https://www.cnblogs.com/qingchunshiguang/p/8011049.html" title="发布于2017-12-09 11:50">一小时搞定DIV+CSS布局-固定页面开度布局</a><br><a href="https://www.cnblogs.com/qingchunshiguang/p/8027396.html" class="p_n_p_prefix">» </a> 下一篇：<a href="https://www.cnblogs.com/qingchunshiguang/p/8027396.html" title="发布于2017-12-12 13:49">display:inline、block、inline-block的区别</a><br></div>
</div>


		</div>
		<div class="postDesc">posted @ <span id="post-date">2017-12-09 12:08</span> <a href="https://www.cnblogs.com/qingchunshiguang/">青春时光</a> 阅读(<span id="post_view_count">51150</span>) 评论(<span id="post_comment_count">4</span>)  <a href="https://i.cnblogs.com/EditPosts.aspx?postid=8011103" rel="nofollow">编辑</a> <a href="#" onclick="AddToWz(8011103);return false;">收藏</a></div>
	</div>
	<script type="text/javascript">var allowComments=true,cb_blogId=313580,cb_entryId=8011103,cb_blogApp=currentBlogApp,cb_blogUserGuid='05cc7c23-7e9b-e611-845c-ac853d9f53ac',cb_entryCreatedDate='2017/12/9 12:08:00';loadViewCount(cb_entryId);var cb_postType=1;var isMarkdown=false;</script>
	
</div>
