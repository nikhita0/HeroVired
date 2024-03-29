const { Octokit } = require("@octokit/core");
const express = require("express");
const app = express();
const port = process.env.PORT || 3000;
const cors = require("cors");
const octokit = new Octokit({
  auth: "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",
});

app.use(express.static("public"));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cors());

app.get("/search/repositories", async (req, res) => {
  try {
    const searchTerm = req.query.searchTerm;
    const response = await octokit.request("GET /search/repositories", {
      headers: {
        "X-GitHub-Api-Version": "2022-11-28",
      },
      q: searchTerm,
    });
    const data = response.data;
    res.send(data);
  } catch (error) {
    console.error("Error", error);
    res.status(500).send("Server Error");
  }
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});