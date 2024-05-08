<div id="assignment_show" class="assignment content_underline_links">
    <!--Student View-->
    <div class="assignment-title">
      <div class="title-content">
        <h1 class="title">
          Group Project (REAL)
        </h1>
      </div>
      <div class="assignment-buttons">
<ul class="student-assignment-overview">
  <li>
    <span class="title">Due</span>
    <span class="value">
          <span class="date_text">
                <span class="display_date">May 9</span> by
                <span class="display_time">17:00pm</span>
          </span><!--
        --></span>
  </li>
  <li>
    <span class="title">Points</span>
    <span class="value">35</span>
  </li>
  <div class="clear"></div>
</ul>

  <div class="clear"></div>

  <div class="clear"></div>

  <div class="description user_content enhanced"><p><strong>Course code and name:&nbsp;</strong>COSC2658 - Data Structures and Algorithms</p>
<p><strong>Assessment name:</strong> Group Project</p>
<p><strong>Length:</strong> Source Code</p>
<p><strong>Type:</strong> Group of 4</p>
<p><strong>Feedback mode:</strong>&nbsp;Written feedback</p>
<p><strong>Late work:</strong> For assignments 1 to 5 days late, a penalty of 10% (of the marks awarded) per day will apply. For assignments more than 5 days late, a penalty of 100% will apply. Weekend days (Saturday and Sunday) are counted when counting the total late days.</h2>
<ul>
<li>CLO 1: Compare, contrast, and apply the key algorithmic design paradigms: brute force, divide and conquer, decrease and conquer, transform and conquer, greedy, dynamic programming.</li>
<li>CLO 2: Compare, contrast, and apply key data structures: trees, lists, stacks, queues, hash tables, and graph representations.</li>
<li>CLO 3: Define, compare, analyse, and solve general algorithmic problem types: sorting, searching, string processing, graphs, and geometric.</li>
<li>CLO 4: Compare, contrast, and apply algorithmic tradeoffs: time vs. space, deterministic vs. randomized, and exact vs. approximate.</li>
<li>CLO 5: Implement, empirically compare, and apply fundamental algorithms and data structures to real-world problems.</li>
</ul>
<h2>Ready for Life and Work</h2>
<ul>
<li>Completing this assignment will help you develop the skill to design and implement data structures and algorithms that solve a medium-to-big problem based on a given set of specifications. You will also develop the skill to evaluate the time efficiency of your design and implementation.</li>
</ul>
<hr>
<h2>Assessment Details</h2>
<p><span data-contrast="auto"><strong>Important: </strong>You cannot use the classes/interfaces defined in the Java Collections Framework and external libraries. You can use/extend the examples/solutions I shared on our course's GitHub repo. You are not allowed to use code copied from the Internet.</span></p>
<p><span data-contrast="auto"><strong>Problem:</strong> You are developing an application similar to Google Maps. Among the existing features, you want to optimize the Points of Interest (POI) Search. Basically, given a bounding rectangle (Top Left corner, Width, and Height) and a type of service (e.g., ATMs, Restaurants, Hospitals, etc.), return the set of places providing that service in the bounding rectangle. If there are too many results, you may display K places at the maximum.</span></p>
<p><span data-contrast="auto">Example 1: Search for gas stations near the RMIT campus. In this case, the bounding rectangle can be a square whose center is the RMIT campus. The side length of the square controls the maximum distance from a gas station in the result to the RMIT campus.</span></p>
<p><span data-contrast="auto">Example 2: Search for coffee shops within walking distance of your current location. In this case, the bounding rectangle can be a square whose center is your current location. Depending on how far you are willing to walk, the side length of the square can be set accordingly.</span></p>
<h3><span data-contrast="auto">Technical Description</span></h3>
<ul>
<li>Map size: 10,000,000 x 10,000,000</li>
<li>Bounding rectangle: smallest 100 x 100 and biggest 100,000 x 100,000</li>
<li>Service types: up to 10</li>
<li>Number of places: up to 100,000,000</li>
<li>Maximum places shown in the search result: 50</li>
<li>Euclidean distance is used in this project (https://en.wikipedia.org/wiki/Euclidean_distance)</li>
</ul>
<p><span data-contrast="auto">Create a Map2D ADT. It must support the following operations:</span></p>
<ul>
<li>Add: add a place to the map. For each place, its coordinate (X, Y) and service type are needed. A place can offer multiple services.</li>
<li>Edit: edit a place. Only the services provided can be changed. X and Y are fixed.</li>
<li>Remove: remove a place.</li>
<li>Search: search for a list of places as described above.</li>
</ul>
<p><span data-contrast="auto">You can assume all data entered is valid and requires no validation.:</span></p>
<p><span data-contrast="auto">Testing: you can generate random data to test your work.</span></p>
<p><span data-contrast="auto">Note: you can reduce the size of the problem (for example, map size to 1000 x 1000), but certainly, you cannot earn the full score :).</span></p>
<h3>Deliverables</h3>
<p>You need to provide me with the following outputs regarding the system you develop</p>
<ul>
<li>A README.txt file that describes the contribution score of all members (explained in the Contribution Score section below) and contains the link to the project video (explained in the Video Demonstration section below)</li>
<li>Source code: Java source code</li>
<li>Technical report: a PDF document that contains the following sections
<ul>
<li>An overview and high-level design of your system (Java classes, methods, their relationships, software design patterns, etc.)</li>
<li>The data structures and algorithms you apply or develop (note: you are not restricted to the data structures and algorithms covered in this course; you can create your own data structures and algorithms). You need to describe in detail the working of your data structures and algorithms.</li>
<li>Complexity analysis: you need to provide the complexity analysis of the algorithms you proposed.</li>
<li>Evaluation: you need to describe how you evaluate the correctness and efficiency of your system experimentally.</li>
</ul>
</li>
<li>Video demonstration: create a short video (around 5 minutes and less than 8 minutes) to show a demo of your system. You have to upload your video to YouTube and present its URL in the REAME.txt file.</li>
</ul>
<h3>Contribution Score</h3>
<p>The starting score for each student is 5 points. Each group can decide to add/remove points to/from each member, but the total point of the whole group is kept unchanged (it is = (the number of members) * 5). Additional rules:</p>
<ul>
<li>The maximum point for a member is 7.</li>
<li>If a member gets zero points => that member gets zero for the whole group project assignment. In this case, the total point of the whole group = (the number of members whose scores > zero) * 5.</li>
<li>The contribution score must be agreed upon by all members. If there are disagreements, you must inform the lecturer/coordinator before the due time.</li>
<li>The maximum score for the whole project is 35. If you get more than 35 (due to a high contribution score), the final score is 35.</li>
</ul>
<h3>Support Resources</h3>
<p>This assessment requires that you meet RMIT's expectations for academic integrity. More information and advice on how to avoid plagiarism are available in the Getting Started module.</p>
<p>Open&nbsp;<a href="https://rmit.instructure.com/courses/118548/pages/how-to-succeed-in-data-structures-and-algorithms#fragment-2" data-api-endpoint="https://rmit.instructure.com/api/v1/courses/118548/pages/how-to-succeed-in-data-structures-and-algorithms%23fragment-2" data-api-returntype="Page">the academic integrity page</a>.</p>
<p>Additional library and learning resources are available to help with the assessment in this course</p>
<p>Link to&nbsp;<a title="Assignment Support" href="https://rmit.instructure.com/courses/118548/pages/assignment-support" data-api-endpoint="https://rmit.instructure.com/api/v1/courses/118548/pages/assignment-support" data-api-returntype="Page">Assignment Support</a>.</p>
<h3>Submission Instructions</h3>
<ul>
<li>Java source code, PDF report, and README.txt file should be placed and submitted as a single zip file on Canvas</li>
</ul>
</div>
</div>
</div>
